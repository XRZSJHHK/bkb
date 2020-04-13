<template>
  <div class="layout">
    <Layout>
      <Header>
        <Menu mode="horizontal" theme="dark" active-name="1">
          <!--          <div><img class="layout-logo" src="./assets/logo.png"/></div>-->
          <div class="layout-logo">报考吧</div>
          <div class="layout-nav">
            <MenuItem name="1">
              <div v-if="this.$store.state.token==''">未登录</div>
              <div v-else>您好，{{this.$store.state.userName}}</div>
            </MenuItem>
            <MenuItem name="2" @click.native="login()">
              <Icon type="ios-navigate"></Icon>
              登录/注册
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
  </div>
</template>

<script type="text/javascript">
  import index from './view/index'
  import retrieve from './view/retrieve'
  import crawling from './view/crawling'

  export default {
    name: '',
    components: {
      index,
      retrieve,
      crawling,
    },
    data() {
      return {}
    },
    mounted() {
      //this.toIndex();
    },
    methods: {
      login() {
        this.$store.commit("changeToken", "JSDALKFJLAKDJF")
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
