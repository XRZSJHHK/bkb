<template>
  <div>
    <Breadcrumb style="margin-top:24px;margin-bottom:0 !important;">
      <BreadcrumbItem>首页</BreadcrumbItem>
      <BreadcrumbItem>资源下载</BreadcrumbItem>
    </Breadcrumb>
    <div style="background-color:#fff;margin-bottom:10px;padding-bottom:10px;">
      &#160;&#160;&#160;&#160;&#160;&#160;
      <Input v-model="searchValue1" clearable size="small" suffix="ios-search" placeholder="请输入关键词…"
             style="width:300px;"/>
      <Button type="primary" style="height:24px;float:none !important;margin-top:0 !important;"
              @click="">搜索
      </Button>
      <span v-if="this.$store.state.userIdentity == 1">
      <Button type="primary" style="height:24px;float:none !important;margin-top:0 !important;"
              @click="drawer=true">上传资源
      </Button>
      </span>
    </div>
    <Content :style="{padding: '24px', minHeight: '600px', background: '#fff'}">
      <div class="message">找到资源<span>{{resourceNumber}}</span>个</div>
      <div>
        <Table size="small" :columns="columns" :data="data1">
          <template slot-scope="{ row, index }" slot="action">
            <span style="cursor: pointer">
                <a v-bind:href="row.resourceUrl"
                   download>下载</a>
            </span>
          </template>
        </Table>
      </div>
    </Content>
    <div class="page">
      <Page :total="100" show-sizer show-elevator/>
    </div>
    <Drawer title="上传资源" :closable="false" v-model="drawer" width="600">
      <Upload
        :on-success="uploadSuccess"
        type="drag"
        action="/api/upload">
        <div style="padding: 20px 0">
          <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
          <p>Click or drag files here to upload</p>
        </div>
      </Upload>
    </Drawer>
  </div>
</template>

<script type="text/javascript">

  import axios from 'axios'
  import '../assets/css/common.less'

  export default {
    name: '',
    data() {
      return {
        searchValue1: '',
        drawer: false,
        resourceNumber:0,
        columns: [
          {
            title: '名称',
            key: 'resourceName',
            width: 500
          },
          {
            title: '大小',
            key: 'resourceSize',
          },
          {
            title: '上传时间',
            key: 'resourceUpdateTime',
          },
          {
            title: '操作',
            slot: 'action',
            align: 'center'
          }
        ],
        data1: [],
      }
    },
    mounted(){
      this.getResource();
    },
    methods: {
      uploadSuccess(response) {
        if(response==1){
          this.$Message.success("上传成功");
          this.getResource();
        }else{
          this.$Message.success("上传失败");
        }
      },
      getResource(){
        this.data1=[];
        axios({
          url: '/api/download',
          method: 'get',
          params: {

          },
          dataType: 'json',
        }).then((res) => {
          if(res==''){
            this.$Message.info("没有查找到结果");
          }else{
            this.resourceNumber=res.data.length;
            res.data.forEach(this.formatSize);
            this.data1=res.data;
          }
        }).catch((error) => {
          this.$Message.error(error);
        });
      },
      formatSize(item,index){
        if(item.resourceSize<1048576){
          item.resourceSize=(item.resourceSize/1024).toFixed(2)+' KB';
        }else{
          item.resourceSize=(item.resourceSize/1048576).toFixed(2)+' MB';
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
    padding: 20px 0;
    margin-left: 4px;

    span {
      color: #4466EB;
    }
  }

  button {
    margin-top: -6px;
    float: right;
    /*border: 1px solid #4466EB;*/
    /*color: #4466EB;*/
    height: 36px;
    /*width: 80px;*/
    /*text-align: center;*/
    border-radius: 0 !important;
    margin-right: 10px;
  }

</style>
