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
          <Icon type="ios-paper"></Icon>
          专业介绍
        </Menu-item>
        <Menu-item key="2" @click.native="module2()" name="222">
          <Icon type="ios-people"></Icon>
          专业评论
        </Menu-item>
        <Menu-item key="3" @click.native="module3()" name="333">
          <Icon type="settings"></Icon>
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
        modal1:false,
        theme1: 'light',
        flag: 1,
        module1_data: '',
        module2_data: [],
        module3_data: {},
        commentContent:'',
        nowDate:'',
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
        axios({
          url: '/api/majorAnalysis',
          method: 'get',
          params: {
            majorId:this.$store.state.majorId
          },
          dataType: 'json',
        }).then((res) => {
          this.module3_data=res.data;
        }).catch((error) => {
          this.$Message.error(error);
        });
      },
      show(index) {
        this.$router.push('/school/major');
      },
      add_comment() {
        if(this.$store.state.token==''){
          this.$Message.error("请先登录");
        }else{
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
      cancel() {},

      lineSimple() {
        var lineSimple_data=[];
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
            data:lineSimple_data,
            type: 'line'
          }]
        };
        if (option && typeof option === "object") {
          myChart.setOption(option, true);
        }
      },
      barSimple() {
        var barSimple_data=[];
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
            data:barSimple_data,
            type: 'bar'
          }]
        };
        if (option && typeof option === "object") {
          myChart.setOption(option, true);
        }
      },
    }
  }
</script>

<style scoped>

</style>
