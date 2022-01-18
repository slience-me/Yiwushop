<template>
  <page-header-wrapper content="本页根据昨日数据来计算">
    <a-row :gutter="24" style="margin-top: 10px">
      <a-col :sm="24" :md="12" :xl="8" :style="{ marginBottom: '24px' }">
        <chart-card :loading="loading" title="机构数据汇总" :total="newOrgNum | NumberFormat" class="customChartCard">
          <a-tooltip title="昨日新增机构数" slot="action">
            <a-icon type="info-circle-o" />
          </a-tooltip>
          <template slot="footer">昨日新增机构数</template>
        </chart-card>
      </a-col>
      <a-col :sm="24" :md="12" :xl="8" :style="{ marginBottom: '24px' }">
        <chart-card :loading="loading" title="机构数据汇总" :total="totalOrgNum | NumberFormat" class="customChartCard">
          <a-tooltip title="总机构数" slot="action">
            <a-icon type="info-circle-o" />
          </a-tooltip>
          <template slot="footer">总机构数</template>
        </chart-card>
      </a-col>
      <a-col :sm="24" :md="12" :xl="8" :style="{ marginBottom: '24px' }">
        <chart-card :loading="loading" title="机构数据汇总" :total="activeOrgNum | NumberFormat" class="customChartCard">
          <a-tooltip title="昨日活跃机构数" slot="action">
            <a-icon type="info-circle-o" />
          </a-tooltip>
          <template slot="footer">昨日活跃机构数</template>
        </chart-card>
      </a-col>
    </a-row>

    <a-card :loading="chartLoading1" :bordered="false" :body-style="{padding: '0'}">
      <div class="salesCard">
        <div class="extra-wrapper" slot="tabBarExtraContent">
          <div class="extra-item">
            <a-button-group style="margin-left: 30px">
              <a-button :type="type1" @click="getOrgChart1(30)">最近30天</a-button>
              <a-button :type="type2" @click="getOrgChart1(15)">最近15天</a-button>
              <a-button :type="type3" @click="getOrgChart1(7)">最近7天</a-button>
            </a-button-group>
          </div>
          <a-button v-action:download type="primary" icon="download" size="small" @click="downloadChart1">导出数据</a-button>
        </div>
        <a-row>
          <a-col :xl="24" :lg="24" :md="24" :sm="24" :xs="24">
            <my-line :data="chartData" title="机构增长趋势"></my-line>
          </a-col>
        </a-row>
      </div>
    </a-card>

    <a-card :loading="chartLoading2" :bordered="false" :body-style="{padding: '0'}" style="margin-top: 24px">
      <div class="salesCard">
        <div class="extra-wrapper" slot="tabBarExtraContent">
          <div class="extra-item">
            <a-button-group style="margin-left: 30px">
              <a-button :type="type4" @click="getOrgChart2(30)">最近30天</a-button>
              <a-button :type="type5" @click="getOrgChart2(15)">最近15天</a-button>
              <a-button :type="type6" @click="getOrgChart2(7)">最近7天</a-button>
            </a-button-group>
          </div>
          <a-button v-action:download type="primary" icon="download" size="small" @click="downloadChart2">导出数据</a-button>
        </div>
        <a-row>
          <a-col :xl="24" :lg="24" :md="24" :sm="24" :xs="24">
            <my-line :data="chartData2" title="机构活跃趋势"></my-line>
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
import { getOrg, getOrgChart } from '@/api/data'

export default {
  name: 'DepartmentData',
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
      activeOrgNum: 0,
      newOrgNum: 0,
      totalOrgNum: 0,
      chartData: {
        columns: ['时间', '数量'],
        rows: []
      },
      chartData2: {
        columns: ['时间', '数量'],
        rows: []
      },
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
    this.getOrgData()
    this.getOrgChart1(30)
    this.getOrgChart2(30)
  },
  methods: {
    getOrgData () {
      this.loading = true
      getOrg().then(res => {
        this.activeOrgNum = res.data.activeOrgNum
        this.newOrgNum = res.data.newOrgNum
        this.totalOrgNum = res.data.totalOrgNum
        this.loading = false
      })
    },
    getOrgChart1 (day) {
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
      getOrgChart({
        days: day
      }).then(res => {
        const barData = []
        res.data.map(item => {
          barData.push({
            '时间': item.summaryDate,
            '数量': item.newOrgNum
          })
        })
        this.chartData.rows = barData
        setTimeout(() => {
          this.chartLoading1 = false
        }, 1000)
      })
    },
    getOrgChart2 (day) {
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
      getOrgChart({
        days: day
      }).then(res => {
        const barData = []
        res.data.map(item => {
          barData.push({
            '时间': item.summaryDate,
            '数量': item.activeOrgNum
          })
        })
        this.chartData2.rows = barData
        setTimeout(() => {
          this.chartLoading2 = false
        }, 1000)
      })
    },
    downloadChart1 () {
      window.open('http://192.168.200.91:9102/broadcast-platform-service/export/excel/org/growth?days=' + this.day1)
    },
    downloadChart2 () {
      window.open('http://192.168.200.91:9102/broadcast-platform-service/export/excel/org/active?days=' + this.day2)
    }
  }
}
</script>

<style>
  .customChartCard .chart-card-content {
    height: 0px !important;
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
