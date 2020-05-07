<template>
  <div>
    <Breadcrumb :style="{margin: '24px 0'}">
      <BreadcrumbItem>首页</BreadcrumbItem>
      <BreadcrumbItem>热度排行</BreadcrumbItem>
    </Breadcrumb>
    <Content :style="{padding: '24px', minHeight: '600px', background: '#fff'}">
      <div class="message">报考热度排名前<span>{{schoolNumber}}</span>所高校
      </div>
      <div>
        <Table size="small" :columns="columns" :data="data1">
          <template slot-scope="{ row, index }" slot="action">
            <span @click="show(index,row)" style="cursor: pointer">
              <img src="../assets/icon/icon_list_look_normal.png" alt="" style="position: relative; top: 3px">
              <span>查看详情</span>
            </span>
          </template>
        </Table>
      </div>
    </Content>
<!--    <div class="page">-->
<!--      <Page :total="100" show-sizer show-elevator/>-->
<!--    </div>-->
  </div>
</template>

<script>
  import '../assets/css/common.less'
  import axios from 'axios'

  export default {
    name: "crawling",
    data() {
      return {
        columns: [
          {
            title: '排名',
            key: 'rank'
          },
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
            title: '热度值',
            key: 'average'
          },
          {
            title: '操作',
            slot: 'action',
            width: 150,
            align: 'center'
          }
        ],
        data1: [],
        schoolNumber: 0,
      }
    },
    mounted(){
      this.get_data();
    },
    methods: {
      show(index, row) {
        this.$store.commit("changeSchoolId", row.schoolId);
        this.$router.push('/school');
      },
      get_data() {
        axios({
          url: '/api/schoolRank',
          method: 'get',
          params: {

          },
          dataType: 'json',
        }).then((res) => {
          if (res.data == '') {
            this.$Message.info("没有热度排行结果");
          } else {
            res.data.forEach(this.parseData);
            this.data1 = res.data;
            this.schoolNumber = this.data1.length;
          }
        }).catch((error) => {
          this.$Message.error(error);
        });
      },
      parseData(item, index) {
        item.rank=index+1;
        if (item.labelTwo == 1) {
          item.labelTwo = '√'
        } else {
          item.labelTwo = '×'
        }
        if (item.labelNine == 1) {
          item.labelNine = '√'
        } else {
          item.labelNine = '×'
        }
        if (item.labelSelf == 1) {
          item.labelSelf = '√'
        } else {
          item.labelSelf = '×'
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
</style>
