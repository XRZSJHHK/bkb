<template>
  <div>
    <Breadcrumb :style="{margin: '24px 0'}">
      <BreadcrumbItem>首页</BreadcrumbItem>
      <BreadcrumbItem>学校详情</BreadcrumbItem>
      <BreadcrumbItem>专业详情</BreadcrumbItem>
    </Breadcrumb>
    <Content :style="{padding: '24px', minHeight: '600px', background: '#fff'}">
      <Menu mode="horizontal" :theme="theme1" active-key="1">
        <Menu-item key="1" @click.native="module1()" name="111">
          专业介绍
        </Menu-item>
        <Menu-item key="2" @click.native="module2()" name="222">
          专业评论
        </Menu-item>
        <Menu-item key="3" @click.native="module3()" name="333">
          专业分析
        </Menu-item>
      </Menu>
      <div>
        <div v-if="flag===1">
          <br/>
          <span>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;{{module1_data}}</span>
        </div>
        <div v-if="flag===2">
          <div>
            <Card v-for="(item,i) in module2_data" :key="item.id">
              {{item.majorCommentId}}楼&#160;&#160;&#160;&#160;{{item.userName}}：{{item.majorCommentMessage}}&#160;&#160;&#160;&#160;
              <span style="color:darkgrey;font-size:5px;">{{item.majorCommentTime}}</span>
            </Card>
          </div>
          <div @click="add_comment()"
               style="width:30px;height:30px;position:fixed;bottom:100px;right:50px;cursor:pointer">
            <img src="../assets/icon/icon_list_edit_selected.png" style="width:30px;height: 30px;">
          </div>
        </div>
        <div v-if="flag===3">
          <div style="margin-top:10px;margin-bottom:10px;">
            <Button @click="lineSimple()">
              往年录取最低分
            </Button>
            <Button @click="barSimple()">
              往年报考人数
            </Button>
            <Button @click="radar()">
              专业综合评价
            </Button>
            <Button style="margin-left: 100px;">
              <span>{{this.$store.state.schoolName}}</span>
            </Button>
            PK
            <Select v-model="select_pk" style="width:200px">
              <Option v-for="item in school_pk" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </Select>
            <span>{{this.$store.state.majorName}}专业</span>
          </div>
          <div>
            <div id="main" style="height:480px;border:1px solid black;margin:0 auto"></div>
          </div>
        </div>
      </div>
    </Content>
    <Modal
      v-model="modal1"
      title="添加专业评论"
      :mask-closable="false"
      @on-ok="ok"
      @on-cancel="cancel">
      <Input type="textarea" v-model="commentContent" :rows="4" placeholder="请输入..."></Input>
    </Modal>
  </div>
</template>

<script>
  import echarts from 'echarts';
  import axios from 'axios'

  export default {
    name: "major",
    data() {
      return {
        modal1: false,
        theme1: 'light',
        flag: 1,
        module1_data: '',
        module2_data: [],
        module3_data: {},
        module3_pk_data: {},
        commentContent: '',
        nowDate: '',
        select_pk: '',
        school_pk: [],
      }
    },
    watch: {
      select_pk(newValue, oldValue) {
        axios({
          url: '/api/majorAnalysis',
          method: 'get',
          params: {
            majorId: newValue
          },
          dataType: 'json',
        }).then((res) => {
          this.module3_pk_data = res.data;
          var school_pk_name;
          this.school_pk.forEach(function (item, index) {
            if(item.value==newValue){
              school_pk_name=item.label;
            }
          });
          this.radar_pk(school_pk_name);
        }).catch((error) => {
          this.$Message.error(error);
        });
      }
    },
    mounted() {
      this.module1();
    },
    methods: {
      module1() {
        this.flag = 1;
        axios({
          url: '/api/majorIntroduction',
          method: 'get',
          params: {
            majorId: this.$store.state.majorId
          },
          dataType: 'json',
        }).then((res) => {
          this.module1_data = res.data;
        }).catch((error) => {
          this.$Message.error(error);
        });
      },
      module2() {
        this.flag = 2;
        axios({
          url: '/api/majorComment',
          method: 'get',
          params: {
            majorId: this.$store.state.majorId
          },
          dataType: 'json',
        }).then((res) => {
          this.module2_data = res.data;
        }).catch((error) => {
          this.$Message.error(error);
        });
      },
      module3() {
        this.flag = 3;
        this.select_pk = '';
        axios({
          url: '/api/majorAnalysis',
          method: 'get',
          params: {
            majorId: this.$store.state.majorId
          },
          dataType: 'json',
        }).then((res) => {
          this.module3_data = res.data;
        }).catch((error) => {
          this.$Message.error(error);
        });
        // console.log(this.$store.state.majorName);
        axios({
          url: '/api/pk',
          method: 'get',
          params: {
            majorName: this.$store.state.majorName
          },
          dataType: 'json',
        }).then((res) => {
          this.school_pk = res.data;
        }).catch((error) => {
          this.$Message.error(error);
        });
      },
      show(index) {
        this.$router.push('/school/major');
      },
      add_comment() {
        if (this.$store.state.token == '') {
          this.$Message.error("请先登录");
        } else {
          this.commentContent = '';
          this.modal1 = true;
        }
      },
      ok() {
        axios({
          url: 'http://quan.suning.com/getSysTime.do',
          method: 'get',
          params: {},
          dataType: 'json',
        }).then((res) => {
          this.nowDate = res.data.sysTime2;
          //console.log(this.nowDate);
          axios({
            url: '/api/addMajorComment',
            method: 'post',
            params: {
              token: this.$store.state.token,
              majorId: this.$store.state.majorId,
              majorCommentMessage: this.commentContent,
              majorCommentTime: this.nowDate,
            },
            dataType: 'json',
          }).then((res) => {
            //console.log(res.data);
            if (res.data === 1) {
              this.$Message.success("评论发表成功");
              this.module2();
            }
          }).catch((error) => {
            this.$Message.error(error);
          });
        }).catch((error) => {
          this.$Message.error(error);
        });
      },
      cancel() {
      },

      lineSimple() {
        var lineSimple_data = [];
        lineSimple_data.push(this.module3_data.lowScoreSixteen);
        lineSimple_data.push(this.module3_data.lowScoreSeventeen);
        lineSimple_data.push(this.module3_data.lowScoreEighteen);
        lineSimple_data.push(this.module3_data.lowScoreNineteen);
        lineSimple_data.push(this.module3_data.lowScoreTwenty);
        var dom = document.getElementById("main");
        var myChart = echarts.init(dom);
        var app = {};
        var option = null;
        option = {
          title: {
            text: '往年录取最低分'
          },
          xAxis: {
            type: 'category',
            data: ['2016年', '2017年', '2018年', '2019年', '2020年']
          },
          yAxis: {
            type: 'value'
          },
          series: [{
            // data: [620, 932, 901, 934, 1290, 1330],
            data: lineSimple_data,
            type: 'line'
          }]
        };
        if (option && typeof option === "object") {
          myChart.setOption(option, true);
        }
      },
      barSimple() {
        var barSimple_data = [];
        barSimple_data.push(this.module3_data.signUpSixteen);
        barSimple_data.push(this.module3_data.signUpSeventeen);
        barSimple_data.push(this.module3_data.signUpEighteen);
        barSimple_data.push(this.module3_data.signUpNineteen);
        barSimple_data.push(this.module3_data.signUpTwenty);
        var dom = document.getElementById("main");
        var myChart = echarts.init(dom);
        var app = {};
        var option = null;
        option = {
          title: {
            text: '往年报考人数'
          },
          xAxis: {
            type: 'category',
            data: ['2016年', '2017年', '2018年', '2019年', '2020年']
          },
          yAxis: {
            type: 'value'
          },
          series: [{
            //data: [620, 932, 901, 934, 1290, 1330],
            data: barSimple_data,
            type: 'bar'
          }]
        };
        if (option && typeof option === "object") {
          myChart.setOption(option, true);
        }
      },
      radar() {
        var signUpSixteen = parseFloat(this.module3_data.signUpSixteen);
        var signUpSeventeen = parseFloat(this.module3_data.signUpSeventeen);
        var signUpEighteen = parseFloat(this.module3_data.signUpEighteen);
        var signUpNineteen = parseFloat(this.module3_data.signUpNineteen);
        var signUpTwenty = parseFloat(this.module3_data.signUpTwenty);
        var signUp = (signUpSixteen + signUpSeventeen + signUpEighteen + signUpNineteen + signUpTwenty) / 5;
        var lowScoreSixteen = parseFloat(this.module3_data.lowScoreSixteen);
        var lowScoreSeventeen = parseFloat(this.module3_data.lowScoreSeventeen);
        var lowScoreEighteen = parseFloat(this.module3_data.lowScoreEighteen);
        var lowScoreNineteen = parseFloat(this.module3_data.lowScoreNineteen);
        var lowScoreTwenty = parseFloat(this.module3_data.lowScoreTwenty);
        var lowScore = (lowScoreSixteen + lowScoreSeventeen + lowScoreEighteen + lowScoreNineteen + lowScoreTwenty) / 5;
        var one;//报考热度
        if (signUp < 5000) {
          one = (5000.0 / signUp).toFixed(2);
        } else if (signUp < 30000) {
          one = (30000.0 / signUp + 1).toFixed(2);
        } else if (signUp < 55000) {
          one = (55000.0 / signUp + 2).toFixed(2);
        } else if (signUp < 80000) {
          one = (80000.0 / signUp + 3).toFixed(2);
        } else if (signUp < 105000) {
          one = (105000.0 / signUp + 4).toFixed(2);
        } else {
          one = 5.00;
        }
        var two;//专业实力
        if (lowScore < 300) {
          two = (300.0 / lowScore).toFixed(2);
        } else if (lowScore < 320) {
          two = (320.0 / lowScore + 1).toFixed(2);
        } else if (lowScore < 340) {
          two = (340.0 / lowScore + 2).toFixed(2);
        } else if (lowScore < 360) {
          two = (360.0 / lowScore + 3).toFixed(2);
        } else if (lowScore < 380) {
          two = (380.0 / lowScore + 4).toFixed(2);
        } else {
          two = 5.00;
        }
        var three = (0.2 * one + 0.8 * two).toFixed(2);//录取难度
        var four = (0.8 * one + 0.2 * two).toFixed(2);//就业前景
        var five = (0.5 * one + 0.5 * two).toFixed(2);//推荐指数
        var radar_data = [];
        radar_data.push(one);
        radar_data.push(two);
        radar_data.push(three);
        radar_data.push(four);
        radar_data.push(five);
        var dom = document.getElementById("main");
        var myChart = echarts.init(dom);
        var app = {};
        var option = null;
        option = {
          title: {
            text: '专业综合评价'
          },
          tooltip: {},
          legend: {
            data: ['']
          },
          radar: {
            //shape: 'circle',
            name: {
              textStyle: {
                color: '#fff',
                backgroundColor: '#999',
                borderRadius: 3,
                padding: [3, 5]
              }
            },
            indicator: [
              {name: '报考热度', max: 5},
              {name: '专业实力', max: 5},
              {name: '录取难度', max: 5},
              {name: '就业前景', max: 5},
              {name: '推荐指数', max: 5}
            ]
          },
          series: [{
            name: '',
            type: 'radar',
            // areaStyle: {normal: {}},
            data: [
              {
                value: radar_data,
                name: ''
              },
            ]
          }]
        };
        ;
        if (option && typeof option === "object") {
          myChart.setOption(option, true);
        }
      },
      radar_pk(school_pk_name) {
        var signUpSixteen1 = parseFloat(this.module3_data.signUpSixteen);
        var signUpSeventeen1 = parseFloat(this.module3_data.signUpSeventeen);
        var signUpEighteen1 = parseFloat(this.module3_data.signUpEighteen);
        var signUpNineteen1 = parseFloat(this.module3_data.signUpNineteen);
        var signUpTwenty1 = parseFloat(this.module3_data.signUpTwenty);
        var signUp1 = (signUpSixteen1 + signUpSeventeen1 + signUpEighteen1 + signUpNineteen1 + signUpTwenty1) / 5;
        var lowScoreSixteen1 = parseFloat(this.module3_data.lowScoreSixteen);
        var lowScoreSeventeen1 = parseFloat(this.module3_data.lowScoreSeventeen);
        var lowScoreEighteen1 = parseFloat(this.module3_data.lowScoreEighteen);
        var lowScoreNineteen1 = parseFloat(this.module3_data.lowScoreNineteen);
        var lowScoreTwenty1 = parseFloat(this.module3_data.lowScoreTwenty);
        var lowScore1 = (lowScoreSixteen1 + lowScoreSeventeen1 + lowScoreEighteen1 + lowScoreNineteen1 + lowScoreTwenty1) / 5;
        var one1;//报考热度
        if (signUp1 < 5000) {
          one1 = (5000.0 / signUp1).toFixed(2);
        } else if (signUp1 < 30000) {
          one1 = (30000.0 / signUp1 + 1).toFixed(2);
        } else if (signUp1 < 55000) {
          one1 = (55000.0 / signUp1 + 2).toFixed(2);
        } else if (signUp1 < 80000) {
          one1 = (80000.0 / signUp1 + 3).toFixed(2);
        } else if (signUp1 < 105000) {
          one1 = (105000.0 / signUp1 + 4).toFixed(2);
        } else {
          one1 = 5.00;
        }
        var two1;//专业实力
        if (lowScore1 < 300) {
          two1 = (300.0 / lowScore1).toFixed(2);
        } else if (lowScore1 < 320) {
          two1 = (320.0 / lowScore1 + 1).toFixed(2);
        } else if (lowScore1 < 340) {
          two1 = (340.0 / lowScore1 + 2).toFixed(2);
        } else if (lowScore1 < 360) {
          two1 = (360.0 / lowScore1 + 3).toFixed(2);
        } else if (lowScore1 < 380) {
          two1 = (380.0 / lowScore1 + 4).toFixed(2);
        } else {
          two1 = 5.00;
        }
        var three1 = (0.2 * one1 + 0.8 * two1).toFixed(2);//录取难度
        var four1 = (0.8 * one1 + 0.2 * two1).toFixed(2);//就业前景
        var five1 = (0.5 * one1 + 0.5 * two1).toFixed(2);//推荐指数
        var radar_pk_data1 = [];
        radar_pk_data1.push(one1);
        radar_pk_data1.push(two1);
        radar_pk_data1.push(three1);
        radar_pk_data1.push(four1);
        radar_pk_data1.push(five1);

        var signUpSixteen2 = parseFloat(this.module3_pk_data.signUpSixteen);
        var signUpSeventeen2 = parseFloat(this.module3_pk_data.signUpSeventeen);
        var signUpEighteen2 = parseFloat(this.module3_pk_data.signUpEighteen);
        var signUpNineteen2 = parseFloat(this.module3_pk_data.signUpNineteen);
        var signUpTwenty2 = parseFloat(this.module3_pk_data.signUpTwenty);
        var signUp2 = (signUpSixteen2 + signUpSeventeen2 + signUpEighteen2 + signUpNineteen2 + signUpTwenty2) / 5;
        var lowScoreSixteen2 = parseFloat(this.module3_pk_data.lowScoreSixteen);
        var lowScoreSeventeen2 = parseFloat(this.module3_pk_data.lowScoreSeventeen);
        var lowScoreEighteen2 = parseFloat(this.module3_pk_data.lowScoreEighteen);
        var lowScoreNineteen2 = parseFloat(this.module3_pk_data.lowScoreNineteen);
        var lowScoreTwenty2 = parseFloat(this.module3_pk_data.lowScoreTwenty);
        var lowScore2 = (lowScoreSixteen2 + lowScoreSeventeen2 + lowScoreEighteen2 + lowScoreNineteen2 + lowScoreTwenty2) / 5;
        var one2;//报考热度
        if (signUp2 < 5000) {
          one2 = (5000.0 / signUp2).toFixed(2);
        } else if (signUp2 < 30000) {
          one2 = (30000.0 / signUp2 + 1).toFixed(2);
        } else if (signUp2 < 55000) {
          one2 = (55000.0 / signUp2 + 2).toFixed(2);
        } else if (signUp2 < 80000) {
          one2 = (80000.0 / signUp2 + 3).toFixed(2);
        } else if (signUp2 < 105000) {
          one2 = (105000.0 / signUp2 + 4).toFixed(2);
        } else {
          one2 = 5.00;
        }
        var two2;//专业实力
        if (lowScore2 < 300) {
          two2 = (300.0 / lowScore2).toFixed(2);
        } else if (lowScore2 < 320) {
          two2 = (320.0 / lowScore2 + 1).toFixed(2);
        } else if (lowScore2 < 340) {
          two2 = (340.0 / lowScore2 + 2).toFixed(2);
        } else if (lowScore2 < 360) {
          two2 = (360.0 / lowScore2 + 3).toFixed(2);
        } else if (lowScore2 < 380) {
          two2 = (380.0 / lowScore2 + 4).toFixed(2);
        } else {
          two2 = 5.00;
        }
        var three2 = (0.2 * one2 + 0.8 * two2).toFixed(2);//录取难度
        var four2 = (0.8 * one2 + 0.2 * two2).toFixed(2);//就业前景
        var five2 = (0.5 * one2 + 0.5 * two2).toFixed(2);//推荐指数
        var radar_pk_data2 = [];
        radar_pk_data2.push(one2);
        radar_pk_data2.push(two2);
        radar_pk_data2.push(three2);
        radar_pk_data2.push(four2);
        radar_pk_data2.push(five2);

        var dom = document.getElementById("main");
        var myChart = echarts.init(dom);
        var app = {};
        var option = null;
        option = {
          title: {
            text: '专业对比'
          },
          tooltip: {},
          legend: {
            //data: ['清华大学', '北京大学']
            data:[this.$store.state.schoolName,school_pk_name]
          },
          radar: {
            // shape: 'circle',
            name: {
              textStyle: {
                color: '#fff',
                backgroundColor: '#999',
                borderRadius: 3,
                padding: [3, 5]
              }
            },
            indicator: [
              {name: '报考热度', max: 5},
              {name: '专业实力', max: 5},
              {name: '录取难度', max: 5},
              {name: '就业前景', max: 5},
              {name: '推荐指数', max: 5}
            ]
          },
          series: [{
            name: '',
            type: 'radar',
            // areaStyle: {normal: {}},
            data: [
              {
                value: radar_pk_data1,
                name: this.$store.state.schoolName
              },
              {
                value: radar_pk_data2,
                name: school_pk_name
              }
            ]
          }]
        };
        ;
        if (option && typeof option === "object") {
          myChart.setOption(option, true);
        }
      },
    }
  }
</script>

<style scoped>

</style>
