<template>
  <div>
    <div style="position: relative">
      <Breadcrumb :style="{margin: '24px 0'}">
        <BreadcrumbItem>首页</BreadcrumbItem>
        <BreadcrumbItem>爬虫管理</BreadcrumbItem>
        <BreadcrumbItem>账户管理</BreadcrumbItem>
      </Breadcrumb>
      <div @click="goBack()" class="return">
        返回
        <img src="../assets/icon/icon_toolbar_back_normal.png" alt="">
      </div>
    </div>
    <Content :style="{padding: '24px', minHeight: '600px', background: '#fff'}">
      <div class="message">账户<span>35</span>个，所监控媒体<span>3</span>个，正在运行<span>30</span>个
        <div style="float: right">
          <Button @click="del" style="margin-right: 10px">删除</Button>
          <Upload
            multiple
            action="//jsonplaceholder.typicode.com/posts/"
            :on-success="success"
            :on-progress="progress"
            :on-error="error">
            <Button @click="import_file">批量导入</Button>
          </Upload>
        </div>
      </div>
      <div>
        <Table size="small" ref="selection" :columns="columns" :data="data1" @on-selection-change="handleSelectOne"></Table>
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
        name: "account",
        data() {
            return {
                columns: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '账户名称',
                        key: 'id'
                    },
                    {
                        title: '创建时间',
                        key: 'create_time'
                    },
                    {
                        title: '上次运行时间',
                        key: 'last_run',
                        sortable: true
                    },
                    {
                        title: '运行状态',
                        key: 'account_state',
                        sortable: true
                    }
                ],
                data1: [
                    {
                        id: 'account-1',
                        create_time: '2019-01-01 8:30:00',
                        last_run: '2019-01-01 8:30:00',
                        account_state: '监控中'
                    },
                    {
                        id: 'account-2',
                        create_time: '2019-01-01 8:30:00',
                        last_run: '2019-01-01 8:30:00',
                        account_state: '监控中'
                    },
                    {
                        id: 'account-3',
                        create_time: '2019-01-01 8:30:00',
                        last_run: '2019-01-01 8:30:00',
                        account_state: '监控中'
                    },
                    {
                        id: 'account-4',
                        create_time: '2019-01-01 8:30:00',
                        last_run: '2019-01-01 8:30:00',
                        account_state: '监控中'
                    },
                    {
                        id: 'account-5',
                        create_time: '2019-01-01 8:30:00',
                        last_run: '2019-01-01 8:30:00',
                        account_state: '监控中'
                    },
                    {
                        id: 'account-6',
                        create_time: '2019-01-01 8:30:00',
                        last_run: '2019-01-01 8:30:00',
                        account_state: '监控中'
                    }
                ],
                id_group: [],
                file_path: '',
                account_management_data:[],
                index_group: [],
                flag: false
            }
        },
        methods: {
            success (response,file,fileList) {
                console.log(response)
              // alert('上传成功')
            },
            progress (event,file,fileList) {
                if (this.flag === false) {
                    const msg = this.$Message.loading({
                        content: '正在上传..',
                        duration: 0
                    });
                    setTimeout(msg, 2000);
                }
                this.flag = true
            },
            error (error) {
                console.log(error)
            },
            async get_account_management() {
                const {data} = await axios.get('')
                console.log(data)
                this.data = data
            },
            goBack () {
                this.$router.go(-1);
            },
            handleSelectOne (selection) {
                // console.log(selection)
                this.account_management_data=selection
            },
            async del () {
                // this.account_management_data.forEach((item) => {
                //     this.id_group.push(item.id)
                // })
                // console.log(this.id_group)
                // const {data} = await axios.get('',{
                //     params: {
                //         id: this.id_group
                //     }
                // })
                // // console.log(data)
                // this.get_account_management()
            },
            async import_file () {
                this.flag = false
                const {data} = await axios.get('',{
                    params: {
                        file_path: this.file_path
                    }
                })
                console.log(data)
            }

        },
        mounted () {
            this.get_account_management()
        }
    }
</script>

<style scoped lang="less">
  .return {
    cursor: pointer;
    position: absolute;
    float: right;
    top: 15px;
    right: 35px;
  }

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
    border: 1px solid #4466EB;
    color: #4466EB;
    height: 36px;
    border-radius: 0 !important;
  }

  .ivu-upload {
    display: inline-block;

    // 倒入列表隐藏 暂无设计图
    /deep/.ivu-upload-list {
      display: none;
    }
  }


</style>
