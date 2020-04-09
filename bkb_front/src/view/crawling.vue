<template>
  <div>
    <Breadcrumb :style="{margin: '24px 0'}">
      <BreadcrumbItem>首页</BreadcrumbItem>
      <BreadcrumbItem>热度排行</BreadcrumbItem>
    </Breadcrumb>
    <Content :style="{padding: '24px', minHeight: '600px', background: '#fff'}">
      <div class="message">报考热度排名前<span>100</span>所高校
<!--        <Button @click="add_keyWord" type="primary">添加关键词</Button>-->
      </div>
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
        name: "crawling",
        data() {
            return {
              columns: [
                {
                  title: '排名',
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
                  abstract: '清华大学',
                  platform: '211、985',
                  date: '一流高校'
                },
                {
                  title: '03',
                  abstract: '蓝翔',
                  platform: '否',
                  date: '否'
                },
                {
                  title: '04',
                  abstract: '新东方',
                  platform: '否',
                  date: '否'
                }
              ],
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
