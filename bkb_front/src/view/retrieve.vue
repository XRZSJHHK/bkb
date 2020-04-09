<template>
  <div>
    <Breadcrumb style="margin-top:24px;margin-bottom:0 !important;">
      <BreadcrumbItem>首页</BreadcrumbItem>
      <BreadcrumbItem>查找学校</BreadcrumbItem>
    </Breadcrumb>
    <div style="background-color:#fff;margin-bottom:10px;padding-bottom:10px;">
      &#160;&#160;&#160;&#160;&#160;&#160;
      <Input v-model="searchValue1" clearable size="small" suffix="ios-search" placeholder="请输入学校名称…"
             style="width:300px;"/>
      &#12288;&#12288;211/985
      <Select v-model="selectValue2" size="small" style="width:100px">
        <Option v-for="item in selectList2" :value="item.value" :key="item.value">{{ item.label }}</Option>
      </Select>
      &#12288;&#12288;双一流
      <Select v-model="selectValue3" size="small" style="width:100px">
        <Option v-for="item in selectList3" :value="item.value" :key="item.value">{{ item.label }}</Option>
      </Select>
      &#160;
      <Button type="primary" style="height:24px;float:none !important;margin-top:0 !important;"
              @click="set_data_search">搜索
      </Button>
    </div>
    <Content :style="{padding: '24px', minHeight: '600px', background: '#fff'}">
      <div class="message">根据您的条件，已为您筛选出<span>2</span>所高校</div>
      <div>
        <Table size="small" :columns="columns" :data="data">
          <template slot-scope="{ row, index }" slot="action">
            <span @click="show(index)" style="cursor: pointer">
              <img src="../assets/icon/icon_list_look_normal.png" alt="" style="position: relative; top: 3px">
              <span>查看详情</span>
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

<script>
  import '../assets/css/common.less'
  import axios from 'axios'

  export default {
    name: "retrieve",
    data() {
      return {
        columns: [
          {
            title: '编号',
            key: 'title'
          },
          {
            title: '学校名称',
            key: 'abstract'
          },
          {
            title: '是否"211/985"',
            key: 'platform'
          },
          {
            title: '是否"双一流"',
            key: 'date'
          },
          {
            title: '操作',
            slot: 'action',
            width: 150,
            align: 'center'
          }
        ],
        data: [
          {
            title: '01',
            abstract: '中国石油大学（华东）',
            platform: '211',
            date: '一流学科'
          },
          {
            title: '02',
            abstract: '中国石油大学（北京）',
            platform: '211',
            date: '一流学科'
          }
        ],
        selectList2: [
          {
            value: '不限',
            label: '不限'
          },
          {
            value: '211',
            label: '211'
          },
          {
            value: '985',
            label: '985'
          },
        ],
        selectList3: [
          {
            value: '不限',
            label: '不限'
          },
          {
            value: '一流高校',
            label: '一流高校'
          },
          {
            value: '一流学科',
            label: '一流学科'
          },
        ],
        searchValue1: '',
        selectValue2: '不限',
        selectValue3:'不限',
        timeRange: [],
        select_date: '',
        file_path: ''
      }
    },
    methods: {
      show(index) {
        this.$router.push('/school');
      },
      get_date(data) {
        this.select_date = data
      },
      async data_export() {
        // const {data} = await axios.get('', {
        //   file_path: this.file_path,
        // })
      },

      async set_data_search() {
        const {data} = await axios.get('', {
          keywords: this.searchValue1,
          platform: this.selectValue2,
          date: this.select_date
        })
        // console.log(data)
      }
    }
  }
</script>

<style scoped lang="less">

  .message {
    height: 58px;
    font-family: "Microsoft YaHei";
    font-size: 14px;
    color: #69717a;
    padding: 16px 0;
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
