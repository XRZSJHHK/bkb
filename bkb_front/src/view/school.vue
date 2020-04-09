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
            <Card v-for="(item,i) in module2_data" :key="item.id">
              {{item.id}}楼&#160;&#160;&#160;&#160;{{item.user_name}}：{{item.comment}}
            </Card>
          </div>
          <div @click="add_comment()" style="width:30px;height:30px;position:fixed;bottom:100px;right:50px;cursor:pointer">
            <img src="../assets/icon/icon_list_edit_selected.png" style="width:30px;height: 30px;">
          </div>
        </div>
        <div v-if="flag===3">
          <div>
            <Table size="small" :columns="columns" :data="module3_data">
              <template slot-scope="{ row, index }" slot="action">
            <span @click="show(index)" style="cursor: pointer">
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
      <Input type="textarea" :rows="4" placeholder="请输入..."></Input>
    </Modal>
  </div>
</template>

<script>
  export default {
    name: "school",
    data() {
      return {
        modal1: false,
        theme1: 'light',
        flag: 1,
        module1_data: "中国石油大学是教育部直属全国重点大学，是国家“211工程”重点建设和开展“985工程优势学科创新平台”建设并建有研究生院的高校之一。2017年学校进入国家“双一流”建设高校行列。中国石油大学（华东）是教育部和五大能源企业集团公司、教育部和山东省人民政府共建的高校，是石油石化高层次人才培养的重要基地，被誉为“石油科技、管理人才的摇篮”，现已成为一所以工为主、石油石化特色鲜明、多学科协调发展的大学。",
        module2_data:
          [
            {id: 1, user_name: "张三", comment: '中国石油大学（华东）这所学校很漂亮'},
            {id: 2, user_name: "李四", comment: '一楼说的对'},
            {id: 3, user_name: "王二", comment: '同意二楼的话'},
            {id: 4, user_name: "麻子", comment: '三楼没毛病'},
          ],
        columns: [
          {
            title: '编号',
            key: 'title'
          },
          {
            title: '专业名称',
            key: 'abstract'
          },
          {
            title: '操作',
            slot: 'action',
            width: 150,
            align: 'center'
          }
        ],
        module3_data: [
          {
            title: '001',
            abstract: '软件工程',
          },
          {
            title: '002',
            abstract: '计算机科学与技术',
          },
          {
            title: '003',
            abstract: '物联网工程',
          }
        ],
      }
    },
    methods: {
      module1() {
        this.flag = 1;
      },
      module2() {
        this.flag = 2;
      },
      module3() {
        this.flag = 3;
      },
      show(index){
        this.$router.push('/school/major');
      },
      add_comment(){
        this.modal1=true;
      },
      ok () {
        this.$Message.info('点击了确定');
      },
      cancel () {
        this.$Message.info('点击了取消');
      }
    }
  }
</script>

<style scoped>

</style>
