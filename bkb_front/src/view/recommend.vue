<template>
  <div>
    <Breadcrumb :style="{margin: '24px 0'}">
      <BreadcrumbItem>首页</BreadcrumbItem>
      <BreadcrumbItem>报考推荐</BreadcrumbItem>
    </Breadcrumb>
    <Content :style="{padding: '24px', minHeight: '600px', background: '#fff'}">
      <div class="message">来自后台统计，最受关注的前<span>15</span>名院校和专业</div>
      <div>
        <Row>
          <Col span="11">
            <Card  Style="height: 430px;">
              <p slot="title">最受关注的院校：</p>
              <div v-for="(item,i) in school" style="display: flex">
                <div style="width:50px;">{{i+1}}</div>
                <div style="width:270px;">{{item.schoolName}}</div>
                <div style="width:150px;">访问量：<span style="color:#4466EB">{{item.click}}</span>&#160;次</div>
              </div>
            </Card>
          </Col>
          <Col span="11" offset="2">
            <Card  Style="height: 430px;">
              <p slot="title">最受关注的专业：</p>
              <div v-for="(item,i) in major" style="display: flex">
                <div style="width:50px;">{{i+1}}</div>
                <div style="width:270px;">{{item.majorName}}</div>
                <div style="width:150px;">访问量：<span style="color:#4466EB">{{item.click}}</span>&#160;次</div>
              </div>
            </Card>
          </Col>
        </Row>
      </div>
    </Content>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: "recommend",
    data() {
      return {
        school:[],
        major:[],
      }
    },
    created(){

    },
    mounted(){
      this.init1();
      this.init2();
    },
    methods:{
      init1(){
        axios({
          url: '/api/click/schoolAll',
          method: 'get',
          params: {

          },
          dataType: 'json',
        }).then((res) => {
          this.school=res.data;
        });
      },
      init2(){
        axios({
          url: '/api/click/majorAll',
          method: 'get',
          params: {

          },
          dataType: 'json',
        }).then((res) => {
          this.major=res.data;
        });
      }
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

    span {
      color: #4466EB;
    }
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

  .con{
    font-size:15px;
  }
</style>
