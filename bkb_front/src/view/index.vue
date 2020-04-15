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
    </div>
    <Content :style="{padding: '24px', minHeight: '600px', background: '#fff'}">
      <div class="message">共有资源<span>35</span>个</div>
      <div>
        <Table size="small" :columns="columns" :data="data">
          <template slot-scope="{ row, index }" slot="action">
            <span @click="show(index)" style="cursor: pointer">
              <span>查看说明</span>
            </span>
            <span style="cursor: pointer">
                <a href="http://152.136.125.45/SheetJSTableExport.xlsx"
                   download="data.xlsx">下载</a>
            </span>
          </template>
        </Table>
      </div>
    </Content>
    <div class="page">
      <Page :total="100" show-sizer show-elevator/>
    </div>
  </div>
</template>

<script type="text/javascript">

  import axios from 'axios'
  import '../assets/css/common.less'

  export default {
    name: '',
    data() {
      return {
        searchValue1:'',
        columns: [
          {
            title: '名称',
            key: 'spider_name'
          },
          {
            title: '大小',
            key: 'start_time',
          },
          {
            title: '操作',
            slot: 'action',
            width: 250,
            align: 'center'
          }
        ],
        data: [
          {
            spider_name: '考研资料1',
            start_time: '10M',
          },
          {
            spider_name: '考研资料2',
            start_time: '15M',
          },
          {
            spider_name: '考研资料3',
            start_time: '6.5M',
          },
        ]
      }
    },
    methods: {
      show(index) {
        this.$Modal.info({
          title: '这是标题',
          content: '这是说明内容',
        })
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
