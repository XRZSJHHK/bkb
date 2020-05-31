<template>
  <div>
    <Breadcrumb :style="{margin: '24px 0'}">
      <BreadcrumbItem>首页</BreadcrumbItem>
      <BreadcrumbItem>我的通知</BreadcrumbItem>
    </Breadcrumb>
    <Content :style="{padding: '24px', minHeight: '600px', background: '#fff'}">
      <div class="message">尊敬的用户，为了避免不必要的麻烦，以下通知请仔细阅读哦！！！<span style="float:right">如有问题反馈咨询，请加QQ：<span style="color: #4466EB;">1919141014</span></span></div>
      <div>
        <Timeline>
          <TimelineItem v-for="(item,i) in notice" :key="item.id">
            <p><span class="time">{{item.title}}</span>&#160;&#160;&#160;&#160;<span Style="color:darkgrey;font-size:10px;">{{item.time}}</span></p>
            <p><span class="content">{{item.content}}</span></p>
            <p v-if="$store.state.userIdentity == 1">
              <span style="color:blue;cursor: pointer" @click="deleteNotice(item.id)">删除</span>
            </p>
          </TimelineItem>
        </Timeline>
      </div>
      <div v-if="$store.state.userIdentity == 1" @click="add_notice()"
           style="width:30px;height:30px;position:fixed;bottom:100px;right:50px;cursor:pointer">
        <img src="../assets/icon/icon_list_edit_selected.png" style="width:30px;height: 30px;">
      </div>
    </Content>
    <Modal
      v-model="modal1"
      title="发布新通知"
      :mask-closable="false"
      @on-ok="ok"
      @on-cancel="cancel">
      <Input style="margin-bottom: 10px;" v-model="noticeTitle" placeholder="标题..."></Input>
      <Input type="textarea" v-model="noticeContent" :rows="4" placeholder="内容..."></Input>
    </Modal>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: "helper",
    data() {
      return {
        notice: [],
        modal1:false,
        noticeTitle:'',
        noticeContent:'',
        nowDate:'',
      }
    },
    created(){

    },
    mounted(){
      this.init();
    },
    methods:{
      init(){
        axios({
          url: '/api/notice/get',
          method: 'get',
          params: {

          },
          dataType: 'json',
        }).then((res) => {
          this.notice=res.data;
        })
      },
      add_notice(){
        this.noticeTitle='';
        this.noticeContent='';
        this.modal1=true;
      },
      ok(){
        axios({
          url: 'http://quan.suning.com/getSysTime.do',
          method: 'get',
          params: {},
          dataType: 'json',
        }).then((res) => {
          this.nowDate = res.data.sysTime2;
          //console.log(this.nowDate);
          axios({
            url: '/api/notice/add',
            method: 'post',
            params: {
              title: this.noticeTitle,
              content: this.noticeContent,
              time: this.nowDate,
            },
            headers:{
              token:this.$store.state.token
            },
            dataType: 'json',
          }).then((res) => {
            //console.log(res.data);
            if (res.data === 1) {
              this.$Message.success("发布成功");
              this.init();
            }
          }).catch((error) => {
            this.$Message.error(error);
          });
        }).catch((error) => {
          this.$Message.error(error);
        });
      },
      cancel(){

      },
      deleteNotice(noticeId){
        if(confirm("您确定要删除吗？")){
          axios({
            url: '/api/notice/delete',
            method: 'get',
            params: {
              id:noticeId
            },
            headers:{
              token:this.$store.state.token
            },
            dataType: 'json',
          }).then((res) => {
            if(res.data===1){
              this.$Message.success("删除成功");
              this.init();
            }else{
              this.$Message.error(error);
            }
          })
        }
      },
    }
  }
</script>

<style lang="less" scoped>

  .message {
    height: 58px;
    font-family: "Microsoft YaHei";
    font-size: 14px;
    color: #69717a;
    padding: 18px 0;
    margin-left: 4px;
  }

  button {
    // 这里也许可以优化
    margin-top: -6px;
    float: right;
    /*background-color: #4466EB;*/
    /*border: 1px solid #4466EB;*/
    /*color: #ffffff;*/
    height: 36px;
    /*width: 90px;*/
    border-radius: 0 !important;
    margin-right: 10px;
  }

  .addContent {
    display: flex;
    align-items: center;
    margin-bottom: 12px;
  }

  .time {
    font-size: 14px;
    font-weight: bold;
  }

  .content {
    padding-left: 5px;
  }
</style>
