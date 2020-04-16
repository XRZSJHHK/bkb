<template>
  <div>
    <Breadcrumb :style="{margin: '24px 0'}">
      <BreadcrumbItem>首页</BreadcrumbItem>
      <BreadcrumbItem>学校详情</BreadcrumbItem>
    </Breadcrumb>
    <Content :style="{padding: '24px', minHeight: '600px', background: '#fff'}">
      <Menu mode="horizontal" :theme="theme1" active-key="1">
        <Menu-item key="1" @click.native="module1()" name="111">
          <Icon type="ios-paper"></Icon>
          学校介绍
        </Menu-item>
        <Menu-item key="2" @click.native="module2()" name="222">
          <Icon type="ios-people"></Icon>
          学校评论
        </Menu-item>
        <Menu-item key="3" @click.native="module3()" name="333">
          <Icon type="settings"></Icon>
          选择专业
        </Menu-item>
      </Menu>
      <div>
        <div v-if="flag===1">
          <br/>
          <span>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;{{module1_data}}</span>
        </div>
        <div v-if="flag===2">
          <div>
            <Card v-for="(item,i) in module2_data" :key="item.schoolCommentId">
              {{item.schoolCommentId}}楼&#160;&#160;&#160;&#160;{{item.userName}}：{{item.schoolCommentMessage}}&#160;&#160;&#160;&#160;
              <span style="color:darkgrey;font-size:5px;">{{item.schoolCommentTime}}</span>
            </Card>
          </div>
          <div @click="add_comment()"
               style="width:30px;height:30px;position:fixed;bottom:100px;right:50px;cursor:pointer">
            <img src="../assets/icon/icon_list_edit_selected.png" style="width:30px;height: 30px;">
          </div>
        </div>
        <div v-if="flag===3">
          <div>
            <Table size="small" :columns="columns" :data="module3_data">
              <template slot-scope="{ row, index }" slot="action">
            <span @click="show(index,row)" style="cursor: pointer">
              <img src="../assets/icon/icon_list_look_normal.png" alt="" style="position: relative; top: 3px">
              <span>查看详情</span>
            </span>
              </template>
            </Table>
          </div>
        </div>
      </div>
    </Content>
    <Modal
      v-model="modal1"
      title="添加学校评论"
      :mask-closable="false"
      @on-ok="ok"
      @on-cancel="cancel">
      <Input type="textarea" v-model="commentContent" :rows="4" placeholder="请输入..."></Input>
    </Modal>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    name: "school",
    data() {
      return {
        modal1: false,
        theme1: 'light',
        flag: 1,
        module1_data: '',
        module2_data: [],
        columns: [
          {
            title: '专业名称',
            key: 'majorName'
          },
          {
            title: '操作',
            slot: 'action',
            width: 150,
            align: 'center'
          }
        ],
        module3_data: [],
        commentContent: '',
        nowDate: '获取网络时间失败',
      }
    },
    mounted() {
      this.module1();
    },
    methods: {
      module1() {
        this.flag = 1;
        axios({
          url: '/api/schoolIntroduction',
          method: 'get',
          params: {
            schoolId: this.$store.state.schoolId
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
          url: '/api/schoolComment',
          method: 'get',
          params: {
            schoolId: this.$store.state.schoolId
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
          url: '/api/schoolMajor',
          method: 'get',
          params: {
            schoolId: this.$store.state.schoolId
          },
          dataType: 'json',
        }).then((res) => {
          this.module3_data = res.data;
        }).catch((error) => {
          this.$Message.error(error);
        });
      },
      show(index, row) {
        this.$store.commit("changeMajorId", row.majorId);
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
            url: '/api/addSchoolComment',
            method: 'post',
            params: {
              token: this.$store.state.token,
              schoolId: this.$store.state.schoolId,
              schoolCommentMessage: this.commentContent,
              schoolCommentTime: this.nowDate,
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
    }
  }
</script>

<style scoped>

</style>
