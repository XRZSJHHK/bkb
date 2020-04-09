<template>
  <div>
    <div style="position: relative">
      <Breadcrumb :style="{margin: '24px 0'}">
        <BreadcrumbItem>首页</BreadcrumbItem>
        <BreadcrumbItem>爬虫管理</BreadcrumbItem>
        <BreadcrumbItem>指定监测目标</BreadcrumbItem>
      </Breadcrumb>
      <div @click="goBack()" class="return">
        返回
        <img src="../assets/icon/icon_toolbar_back_normal.png" alt="">
      </div>
    </div>
    <Content :style="{padding: '24px', minHeight: '600px', background: '#fff'}">
      <div class="message">已监测人物<span style="color: #4466EB">35</span>个
        <div class="btn">
          <Button type="primary" @click="modal = true">增加</Button>
        </div>
      </div>
      <div>
        <Table size="small" ref="selection" :columns="columns" :data="data"></Table>
      </div>
    </Content>
    <div class="page">
      <Page :total="100" show-sizer show-elevator/>
    </div>
    <Modal
      v-model="modal"
      title="增添记录"
      width="700"
      @on-ok="add"
      @on-cancel="cancel">
      <Input v-model="add_number" clearable placeholder="序号" style="width: 80px; margin-right:20px"/>
      <Input v-model="add_name" clearable placeholder="名称" style="width: 150px; margin-right: 20px"/>
      <Input v-model="add_platform" clearable placeholder="平台" style="width: 150px; margin-right: 20px"/>
      <Input v-model="add_time" clearable placeholder="监测时间" style="width: 180px"/>
    </Modal>
  </div>
</template>

<script>
    import '../assets/css/common.less'
    import axios from 'axios'

    export default {
        name: "monitor",
        data() {
            return {
                columns: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '序号',
                        key: 'number'
                    },
                    {
                        title: '名称',
                        key: 'name'
                    },
                    {
                        title: '平台',
                        key: 'platform',
                    },
                    {
                        title: '监测时间',
                        key: 'monitor_time',
                    }
                ],
                data: [
                    {
                        number: '01',
                        name: '这里显示名称',
                        platform: '微信公众号',
                        monitor_time: '2019-01-01 8:30:00'
                    },
                    {
                        number: '02',
                        name: '这里显示名称',
                        platform: '微信公众号',
                        monitor_time: '2019-01-01 8:30:00'
                    },
                    {
                        number: '03',
                        name: '这里显示名称',
                        platform: '微信公众号',
                        monitor_time: '2019-01-01 8:30:00'
                    },
                    {
                        number: '04',
                        name: '这里显示名称',
                        platform: '微信公众号',
                        monitor_time: '2019-01-01 8:30:00'
                    },
                ],
                name: '',
                platform: '',
                monitor_time: '',
                modal: false,
                add_number: '',
                add_name: '',
                add_time: '',
                add_platform: ''
            }
        },
        methods: {
            add() {
                this.data.push({
                    number: this.add_number,
                    name: this.add_name,
                    platform: this.add_platform,
                    monitor_time: this.add_time
                })
            },
            cancel() {
                this.modal = false
                this.add_number = ''
                this.add_name = ''
                this.add_time = ''
                this.add_platform = ''
            },
            async get_keyperson_monitor() {
                const {data} = await axios.get('')
                // this.data = data
                console.log(data)
            },
            async add_keyperson_monitor() {
                const {data} = await axios.get('', {
                    name: this.name,
                    platform: this.platform,
                    monitor_time: this.monitor_time
                })
                console.log(data)
            },
            goBack() {
                this.$router.go(-1);
            }
        },
        mounted() {
            this.get_keyperson_monitor()
        }
    }
</script>

<style scoped lang="less">

  button {
    margin-top: -28px;
    float: right;
    /*background-color: #4466EB;*/
    /*border: 1px solid #4466EB;*/
    /*color: #ffffff;*/
    height: 36px;
    /*width: 80px;*/
    border-radius: 0 !important;
    margin-right: 10px;
  }

  // 这可能可以优化
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
    padding: 20px 0;
    margin-left: 4px;
  }

</style>
