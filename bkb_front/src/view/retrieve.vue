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
      <Button type="primary" style="height:24px;float:none !important;margin-top:0 !important;"
              @click="data_search1">搜索
      </Button>
      &#12288;&#12288;211
      <Select v-model="selectValue2" size="small" style="width:80px">
        <Option v-for="item in selectList2" :value="item.value" :key="item.value">{{ item.label }}</Option>
      </Select>
      &#12288;985
      <Select v-model="selectValue3" size="small" style="width:80px">
        <Option v-for="item in selectList3" :value="item.value" :key="item.value">{{ item.label }}</Option>
      </Select>
      &#12288;自划线
      <Select v-model="selectValue4" size="small" style="width:80px">
        <Option v-for="item in selectList4" :value="item.value" :key="item.value">{{ item.label }}</Option>
      </Select>
      &#12288;
      <Button type="primary" style="height:24px;float:none !important;margin-top:0 !important;"
              @click="data_search2">筛选
      </Button>
    </div>
    <Content :style="{padding: '24px', minHeight: '600px', background: '#fff'}">
      <div class="message">根据您的条件，已为您查找出<span>{{schoolNumber}}</span>所高校</div>
      <div>
        <Table size="small" :columns="columns1" :data="data1">
          <template slot-scope="{ row, index }" slot="action">
            <span @click="show(index,row)" style="cursor: pointer">
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
        columns1: [
          {
            title: '学校名称',
            key: 'schoolName'
          },
          {
            title: '是否"211"',
            key: 'labelTwo'
          },
          {
            title: '是否"985"',
            key: 'labelNine'
          },
          {
            title: '是否"自划线"',
            key: 'labelSelf'
          },
          {
            title: '操作',
            slot: 'action',
            width: 150,
            align: 'center'
          }
        ],
        data1: [],
        selectList2: [
          {
            value: 2,
            label: '不限制'
          },
          {
            value: 1,
            label: '是'
          },
          {
            value: 0,
            label: '否'
          },
        ],
        selectList3: [
          {
            value: 2,
            label: '不限制'
          },
          {
            value: 1,
            label: '是'
          },
          {
            value: 0,
            label: '否'
          },
        ],
        selectList4: [
          {
            value: 2,
            label: '不限制'
          },
          {
            value: 1,
            label: '是'
          },
          {
            value: 0,
            label: '否'
          },
        ],
        searchValue1: '',
        selectValue2: 2,
        selectValue3: 2,
        selectValue4: 2,
        schoolNumber: 0,
      }
    },
    methods: {
      show(index,row) {
        this.$store.commit("changeSchoolId",row.schoolId);
        this.$router.push('/school');
      },
      data_search1() {
        this.data1=[];
        axios({
          url: '/api/searchSchool',
          method: 'get',
          params: {
            schoolName: this.searchValue1
          },
          dataType: 'json',
        }).then((res) => {
          if(res.data==''){
              this.$Message.info("没有查找到结果");
          }else{
            if(res.data.labelTwo==1){
              res.data.labelTwo='√'
            }else{
              res.data.labelTwo='×'
            }
            if(res.data.labelNine==1){
              res.data.labelNine='√'
            }else{
              res.data.labelNine='×'
            }
            if(res.data.labelSelf==1){
              res.data.labelSelf='√'
            }else{
              res.data.labelSelf='×'
            }
            this.data1.push(res.data);
            this.schoolNumber = this.data1.length;
          }
        }).catch((error) => {
          this.$Message.error(error);
        });
      },
      data_search2() {
        this.data1=[];
        axios({
          url: '/api/screenSchool',
          method: 'get',
          params: {
            labelTwo:this.selectValue2,
            labelNine:this.selectValue3,
            labelSelf:this.selectValue4
          },
          dataType: 'json',
        }).then((res) => {
          if(res.data==''){
            this.$Message.info("没有查找到结果");
          }else{
            res.data.forEach(this.parseData);
            this.data1=res.data;
            this.schoolNumber = this.data1.length;
          }
        }).catch((error) => {
          this.$Message.error(error);
        });
      },
      parseData(item,index){
        if(item.labelTwo==1){
          item.labelTwo='√'
        }else{
          item.labelTwo='×'
        }
        if(item.labelNine==1){
          item.labelNine='√'
        }else{
          item.labelNine='×'
        }
        if(item.labelSelf==1){
          item.labelSelf='√'
        }else{
          item.labelSelf='×'
        }
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
