<template>
  <page-header-wrapper content="本页根据昨日数据来计算">
    <a-row :gutter="24" style="margin-top: 10px">
      <a-col :sm="24" :md="12" :xl="8" :style="{ marginBottom: '24px' }">
        <chart-card :loading="loading" title="资源数据汇总" :total="newPicNum + newVideoNum | NumberFormat" class="customChartCard1">
          <a-tooltip title="昨日新增资源数" slot="action">
            <a-icon type="info-circle-o" />
          </a-tooltip>
          <div style="display: flex; flex-direction: row">
            <div style="width: 50%; text-align: center; font-size: 22px; font-weight: 500">{{ newVideoNum | NumberFormat }} <span style="font-size: 16px; font-weight: 400">视频</span></div>
            <div style="width: 50%; text-align: center; font-size: 22px; font-weight: 500">{{ newPicNum | NumberFormat }} <span style="font-size: 16px; font-weight: 400">图片</span></div>
          </div>
          <template slot="footer">昨日新增资源数</template>
        </chart-card>
      </a-col>
      <a-col :sm="24" :md="12" :xl="8" :style="{ marginBottom: '24px' }">
        <chart-card :loading="loading" title="资源数据汇总" :total="totalVideoNum + toalPicNum | NumberFormat" class="customChartCard1">
          <a-tooltip title="总资源数" slot="action">
            <a-icon type="info-circle-o" />
          </a-tooltip>
          <div style="display: flex; flex-direction: row">
            <div style="width: 50%; text-align: center; font-size: 22px; font-weight: 500">{{ totalVideoNum | NumberFormat }} <span style="font-size: 16px; font-weight: 400">视频</span></div>
            <div style="width: 50%; text-align: center; font-size: 22px; font-weight: 500">{{ toalPicNum | NumberFormat }} <span style="font-size: 16px; font-weight: 400">图片</span></div>
          </div>
          <template slot="footer">总资源数</template>
        </chart-card>
      </a-col>
      <a-col :sm="24" :md="12" :xl="8" :style="{ marginBottom: '24px' }">
        <chart-card :loading="loading" title="资源数据汇总" :total="activePicNum + activeVideoNum | NumberFormat" class="customChartCard1">
          <a-tooltip title="昨日资源播放次数" slot="action">
            <a-icon type="info-circle-o" />
          </a-tooltip>
          <div style="display: flex; flex-direction: row">
            <div style="width: 50%; text-align: center; font-size: 22px; font-weight: 500">{{ activeVideoNum | NumberFormat }} <span style="font-size: 16px; font-weight: 400">视频</span></div>
            <div style="width: 50%; text-align: center; font-size: 22px; font-weight: 500">{{ activePicNum | NumberFormat }} <span style="font-size: 16px; font-weight: 400">图片</span></div>
          </div>
          <template slot="footer">昨日资源播放次数</template>
        </chart-card>
      </a-col>
    </a-row>

    <a-card :loading="chartLoading1" :bordered="false" :body-style="{padding: '0'}">
      <div class="salesCard">
        <div class="extra-wrapper" slot="tabBarExtraContent">
          <div class="extra-item">
            <a-button-group style="margin-left: 30px">
              <a-button :type="type1" @click="getInfoChart1(30)">最近30天</a-button>
              <a-button :type="type2" @click="getInfoChart1(15)">最近15天</a-button>
              <a-button :type="type3" @click="getInfoChart1(7)">最近7天</a-button>
            </a-button-group>
          </div>
          <a-button v-action:download type="primary" icon="download" size="small" @click="downloadChart1">导出数据</a-button>
        </div>
        <a-row>
          <a-col :xl="24" :lg="24" :md="24" :sm="24" :xs="24">
            <my-line :data="chartData" title="资源增长趋势"></my-line>
          </a-col>
        </a-row>
      </div>
    </a-card>

    <a-card :loading="chartLoading2" :bordered="false" :body-style="{padding: '0'}" style="margin-top: 24px">
      <div class="salesCard">
        <div class="extra-wrapper" slot="tabBarExtraContent">
          <div class="extra-item">
            <a-button-group style="margin-left: 30px">
              <a-button :type="type4" @click="getInfoChart2(30)">最近30天</a-button>
              <a-button :type="type5" @click="getInfoChart2(15)">最近15天</a-button>
              <a-button :type="type6" @click="getInfoChart2(7)">最近7天</a-button>
            </a-button-group>
          </div>
          <a-button v-action:download type="primary" icon="download" size="small" @click="downloadChart2">导出数据</a-button>
        </div>
        <a-row>
          <a-col :xl="24" :lg="24" :md="24" :sm="24" :xs="24">
            <my-line :data="chartData2" title="资源播放次数趋势"></my-line>
          </a-col>
        </a-row>
      </div>
    </a-card>
  </page-header-wrapper>
</template>

<script>
import {
  ChartCard,
  MyLine
} from '@/components'
import { baseMixin } from '@/store/app-mixin'
import { getInfo, getInfoChart } from '@/api/data'

export default {
  name: 'InfoData',
  mixins: [baseMixin],
  components: {
    ChartCard,
    MyLine
  },
  data () {
    return {
      loading: true,
      chartLoading1: true,
      chartLoading2: true,
      chartData: {
        columns: ['时间', '视频', '图片'],
        rows: []
      },
      chartData2: {
        columns: ['时间', '视频', '图片'],
        rows: []
      },
      activePicNum: 0,
      activeVideoNum: 0,
      newPicNum: 0,
      newVideoNum: 0,
      toalPicNum: 0,
      totalVideoNum: 0,
      day1: 30,
      day2: 30,
      type1: 'primary',
      type2: '',
      type3: '',
      type4: 'primary',
      type5: '',
      type6: ''
    }
  },
  created () {
    this.getInfoData()
    this.getInfoChart1(30)
    this.getInfoChart2(30)
  },
  methods: {
    getInfoData () {
      this.loading = true
      getInfo().then(res => {
        this.loading = false
        this.activePicNum = res.data.activePicNum
        this.activeVideoNum = res.data.activeVideoNum
        this.newPicNum = res.data.newPicNum
        this.newVideoNum = res.data.newVideoNum
        this.toalPicNum = res.data.toalPicNum
        this.totalVideoNum = res.data.totalVideoNum
      })
    },
    getInfoChart1 (day) {
      if (day === 30) {
        this.type1 = 'primary'
        this.type2 = ''
        this.type3 = ''
      } else if (day === 15) {
        this.type1 = ''
        this.type2 = 'primary'
        this.type3 = ''
      } else if (day === 7) {
        this.type1 = ''
        this.type2 = ''
        this.type3 = 'primary'
      }
      this.day1 = day
      this.chartLoading1 = true
      getInfoChart({
        days: day
      }).then(res => {
        const barData = []
        res.data.map(item => {
          barData.push({
            '时间': item.summaryDate,
            '视频': item.newVideoNum,
            '图片': item.newPicNum
          })
        })
        this.chartData.rows = barData
        setTimeout(() => {
          this.chartLoading1 = false
        }, 1000)
      })
    },
    getInfoChart2 (day) {
      if (day === 30) {
        this.type4 = 'primary'
        this.type5 = ''
        this.type6 = ''
      } else if (day === 15) {
        this.type4 = ''
        this.type5 = 'primary'
        this.type6 = ''
      } else if (day === 7) {
        this.type4 = ''
        this.type5 = ''
        this.type6 = 'primary'
      }
      this.day2 = day
      this.chartLoading2 = true
      getInfoChart({
        days: day
      }).then(res => {
        const barData = []
        res.data.map(item => {
          barData.push({
            '时间': item.summaryDate,
            '视频': item.activeVideoNum,
            '图片': item.activePicNum
          })
        })
        this.chartData2.rows = barData
        setTimeout(() => {
          this.chartLoading2 = false
        }, 1000)
      })
    },
    downloadChart1 () {
      window.open('http://192.168.200.91:9102/broadcast-platform-service/export/excel/resource/growth?days=' + this.day1)
    },
    downloadChart2 () {
      window.open('http://192.168.200.91:9102/broadcast-platform-service/export/excel/resource/play?days=' + this.day2)
    }
  }
}
</script>

<style>
  .customChartCard1 .chart-card-header .total {
    height: 0 !important;
  }
</style>

<style lang="less" scoped>
  .extra-wrapper {
    line-height: 55px;
    padding-right: 24px;

    .extra-item {
      display: inline-block;
      margin-right: 24px;

      a {
        margin-left: 24px;
      }
    }
  }

  .antd-pro-pages-dashboard-analysis-twoColLayout {
    position: relative;
    display: flex;
    display: block;
    flex-flow: row wrap;
  }

  .antd-pro-pages-dashboard-analysis-salesCard {
    height: calc(100% - 24px);
    /deep/ .ant-card-head {
      position: relative;
    }
  }

  .dashboard-analysis-iconGroup {
    i {
      margin-left: 16px;
      color: rgba(0,0,0,.45);
      cursor: pointer;
      transition: color .32s;
      color: black;
    }
  }
  .analysis-salesTypeRadio {
    position: absolute;
    right: 54px;
    bottom: 12px;
  }
</style>
