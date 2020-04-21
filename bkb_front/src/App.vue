<template>
  <div class="layout">
    <Layout>
      <Header>
        <Menu mode="horizontal" theme="dark" active-name="1">
          <div class="layout-logo">报考吧</div>
          <div class="layout-nav">
            <MenuItem name="1">
              <div v-if="this.$store.state.token===''">未登录</div>
              <div v-else>您好，{{this.$store.state.userName}}</div>
            </MenuItem>
            <MenuItem name="2">
              <div v-if="this.$store.state.token===''" @click="login_register()" style="cursor: pointer">
                <Icon type="ios-navigate"></Icon>
                登录/注册
              </div>
              <div v-else>
                <span @click="logout()" style="cursor: pointer">
                  <Icon type="md-log-out"/>
                注销
                </span>
                <span @click="modifyPassword()" style="cursor: pointer">
                  <Icon type="ios-build"/>
                修改密码
                </span>
              </div>
            </MenuItem>
          </div>
        </Menu>
      </Header>
      <Layout>
        <Sider hide-trigger :style="{background: '#fff'}">
          <Menu active-name="1-2" theme="light" width="auto" :open-names="['1']">
            <MenuItem @click.native="toIndex()" name="1">
              <div class="menu-one">
                <img src="./assets/icon/icon_leftbar_reptile_normal.png" class="img-size">
                资源下载
              </div>
            </MenuItem>
            <MenuItem @click.native="toRetrieve()" name="2">
              <div class="menu-one">
                <img src="./assets/icon/icon_leftbar_datasearch_normal.png" class="img-size">
                查找学校
              </div>
            </MenuItem>
            <MenuItem @click.native="toCrawling()" name="3">
              <div class="menu-one">
                <img src="./assets/icon/icon_leftbar_keyword_normal.png" class="img-size">
                热度排行
              </div>
            </MenuItem>
          </Menu>
        </Sider>
        <Layout :style="{padding: '0 24px 24px'}">
          <router-view name="content"></router-view>
        </Layout>
      </Layout>
    </Layout>
    <Modal
      v-model="modal1"
      :mask-closable="false"
      width="300px;">
      <div>
        <div style="width:200px;margin:0 auto;margin-bottom: 10px!important;">
          <Input type="text" v-model="myUserName" placeholder="Username">
            <Icon type="ios-person-outline" slot="prepend"></Icon>
          </Input>
        </div>
        <div style="width:200px;margin:0 auto">
          <Input type="password" v-model="myUserPassword" placeholder="Password">
            <Icon type="ios-lock-outline" slot="prepend"></Icon>
          </Input>
        </div>
      </div>
      <div slot="header">
        <div v-if="flag1===1"><h3>登录页面</h3></div>
        <div v-else><h3>注册页面</h3></div>
      </div>
      <div slot="footer">
        <div v-if="flag1===1">
          <span style="cursor: pointer" @click="flag1=0">&lt 去注册</span>
          <Button type="primary" style="margin-left:140px;" @click="go_login()">登录</Button>
        </div>
        <div v-else>
          <span style="cursor: pointer" @click="flag1=1">&lt 去登录</span>
          <Button type="primary" style="margin-left:140px;" @click="go_register()">注册</Button>
        </div>
      </div>
    </Modal>
  </div>
</template>

<script type="text/javascript">
  import index from './view/index'
  import retrieve from './view/retrieve'
  import crawling from './view/crawling'
  import axios from 'axios';

  export default {
    name: '',
    components: {
      index,
      retrieve,
      crawling,
    },
    data() {
      return {
        modal1: false,
        flag1: 1,
        myUserName: '',
        myUserPassword: '',
      }
    },
    created() {
      //当sessionStorage里存有store时，取出
      if (sessionStorage.getItem("store")) {
        this.$store.replaceState(
          Object.assign(
            {},
            this.$store.state,
            JSON.parse(sessionStorage.getItem("store"))
          )
        );
        sessionStorage.removeItem("store") //取出后，再清空sessionStorage
      }

      //在页面刷新时将vuex里的信息保存到sessionStorage里
      window.addEventListener("beforeunload", () => {
        sessionStorage.setItem("store", JSON.stringify(this.$store.state));
      });
    },
    mounted() {

    },
    methods: {
      login_register() {
        this.myUserName = '';
        this.myUserPassword = '';
        this.flag1 = 1;
        this.modal1 = true;
      },
      logout() {
        if (confirm("您确定要注销登录吗？")) {
          this.$store.commit("changeUserName", '');
          this.$store.commit("changeUserIdentity", '');
          this.$store.commit("changeToken", '');
          this.$store.commit("changeSchoolId", '');
          this.$store.commit("changeMajorId", '');
        }
      },
      modifyPassword() {
        var newPassword = prompt("请输入新密码");
        if (newPassword == null || newPassword == '') {
          this.$Message.error("修改失败")
        } else {
          axios({
            url: '/api/modifyPassword',
            method: 'post',
            params: {
              token: this.$store.state.token,
              newPassword: newPassword,
            },
            dataType: 'json',
          }).then((res) => {
            if (res.data == 1) {
              this.$Message.success("修改成功");
            } else {
              this.$Message.error("修改失败");
            }
          }).catch((error) => {
            this.$Message.error(error);
          });
        }
      },
      go_login() {
        axios({
          url: '/api/login',
          method: 'post',
          params: {
            userName: this.myUserName,
            userPassword: this.myUserPassword
          },
          dataType: 'json',
        }).then((res) => {
          if (res.data.userPassword === '0') {
            this.$Message.error('用户名不存在');
          } else if (res.data.userPassword === '1') {
            this.$Message.error('密码错误');
          } else {
            this.$store.commit("changeUserName", res.data.userName);
            this.$store.commit("changeUserIdentity", res.data.userIdentity);
            this.$store.commit("changeToken", res.data.token);
            this.modal1 = false;
          }
        }).catch((error) => {
          this.$Message.error(error);
        });
      },
      go_register() {
        axios({
          url: '/api/register',
          method: 'post',
          params: {
            userName: this.myUserName,
            userPassword: this.myUserPassword
          },
          dataType: 'json',
        }).then((res) => {
          if (res.data === -2) {
            this.$Message.error('注册失败，用户名已存在');
          } else if (res.data === 1) {
            this.$Message.success('注册成功');
          }
        }).catch((error) => {
          this.$Message.error(error);
        });
      },
      toIndex() {
        this.$router.push('/index');
      },
      toRetrieve() {
        this.$router.push('/retrieve');
      },
      toCrawling() {
        this.$router.push('/crawling');
      },
    }
  }
</script>

<style lang="less" scoped>
  .layout {
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
  }

  .layout-logo {
    width: 220px;
    height: 40px;
    border-radius: 3px;
    float: left;
    position: relative;
    //top: 11px;
    left: 16px;
    color: white;
    font-size: 30px;
    text-align: center;
  }

  .layout-nav {
    width: 400px;
    height: 60px;
    margin: 0 auto;
    margin-right: 0px;

    .ivu-menu-item {
      text-align: center;
      width: 200px;
      font-family: "Microsoft YaHei";
      font-size: 14px;
      font-weight: bold;
      color: #ffffff;
      cursor: auto !important;
    }
  }

  .ivu-layout-header {
    background-color: #4466EB;
    height: 60px;
    padding: 0;
  }

  .ivu-menu-horizontal {
    background-color: #4466EB;
  }

  .ivu-layout-sider-children {
    .ivu-menu-item {
      font-family: "Microsoft YaHei";
      font-size: 16px;
      font-weight: bold;
      padding: 0;
      width: 250px;
      height: 50px;
    }

    /*.ivu-menu-item:first-of-type {*/
    /*  margin-top: 15px;*/
    /*}*/

    .img-size {
      height: 16px;
      width: 16px;
      margin: 0 6px 0 14px;
      position: relative;
      top: 2px;
    }
  }

  .ivu-layout-sider {
    width: 250px !important;
    max-width: 250px !important;
    min-width: 250px !important;
    flex: 0 0 250px !important;
  }

  .ivu-layout {
    padding: 0 !important;
  }

  .menu-one {
    padding: 15px 0;
  }
</style>
