<template>
  <div id="userLayout" :class="['user-layout-wrapper', isMobile && 'mobile']">
    <div class="container">
      <!--      <div class="top">-->
      <!--        <div class="header">-->
      <!--          <a href="/">-->
      <!--            <img src="~@/assets/logo.svg" class="logo" alt="logo">-->
      <!--            <span class="title">Ant Design</span>-->
      <!--          </a>-->
      <!--        </div>-->
      <!--        <div class="desc">-->
      <!--          Ant Design 是西湖区最具影响力的 Web 设计规范-->
      <!--        </div>-->
      <!--      </div>-->
      <div class="left"/>
      <div class="backImg">
        <router-view />
      </div>
      <!--      <div class="footer">-->
      <!--        <div class="links">-->
      <!--          <a href="_self">帮助</a>-->
      <!--          <a href="_self">隐私</a>-->
      <!--          <a href="_self">条款</a>-->
      <!--        </div>-->
      <!--        <div class="copyright">-->
      <!--          Copyright &copy; 2018 vueComponent-->
      <!--        </div>-->
      <!--      </div>-->
    </div>
  </div>
</template>

<script>
import { deviceMixin } from '@/store/device-mixin'

export default {
  name: 'UserLayout',
  mixins: [deviceMixin],
  mounted () {
    document.body.classList.add('userLayout')
  },
  beforeDestroy () {
    document.body.classList.remove('userLayout')
  }
}
</script>

<style lang="less" scoped>
#userLayout.user-layout-wrapper {
  height: 100%;

  &.mobile {
    .container {
      .main {
        max-width: 368px;
        width: 98%;
      }
    }
  }

  .container {
    width: 100%;
    min-height: 100%;
    background-size: 100%;
    /*padding: 110px 0 144px;*/
    position: relative;
    background-color: white;
    background-color: #eee;
    .left {
      position: absolute;
      left: 0;
      top: 0;
      background-color: #3A62D7;
      background-color: #eee;
      min-height: 100%;
      width: 20%;
    }
    .backImg {
      display: flex;
      align-items: center;
      position: absolute;
      left: 10%;
      top: 10%;
      width: 80%;
      height: 80%;
      background-color: white;
      background-color: #eee;
      background: url(~@/assets/background.svg) no-repeat;
      .main {
        min-width: 260px;
        width: 368px;
        margin: 0 auto;
        /*margin-ri ght: 80px;*/
      }
    }

    a {
      text-decoration: none;
    }

    .top {
      text-align: center;

      .header {
        height: 44px;
        line-height: 44px;

        .badge {
          position: absolute;
          display: inline-block;
          line-height: 1;
          vertical-align: middle;
          margin-left: -12px;
          margin-top: -10px;
          opacity: 0.8;
        }

        .logo {
          height: 44px;
          vertical-align: top;
          margin-right: 16px;
          border-style: none;
        }

        .title {
          font-size: 33px;
          color: rgba(0, 0, 0, .85);
          font-family: Avenir, 'Helvetica Neue', Arial, Helvetica, sans-serif;
          font-weight: 600;
          position: relative;
          top: 2px;
        }
      }
      .desc {
        font-size: 14px;
        color: rgba(0, 0, 0, 0.45);
        margin-top: 12px;
        margin-bottom: 40px;
      }
    }

    .footer {
      position: absolute;
      width: 100%;
      bottom: 0;
      padding: 0 16px;
      margin: 48px 0 24px;
      text-align: center;

      .links {
        margin-bottom: 8px;
        font-size: 14px;
        a {
          color: rgba(0, 0, 0, 0.45);
          transition: all 0.3s;
          &:not(:last-child) {
            margin-right: 40px;
          }
        }
      }
      .copyright {
        color: rgba(0, 0, 0, 0.45);
        font-size: 14px;
      }
    }
  }
}
</style>
