<template>
  <div class="app-container">
    <div>
      <img src="../../../public/homeBack.png" class="emptyGif">
    </div>
  </div>
</template>

<script>
  import { Modal } from 'ant-design-vue'
    export default {
      name: 'DashboardIndex',
      created () {
          Modal.success({
            content: '欢迎登录大学生易物平台后台管理系统！🤗🤗🤗'
          })
      }
    }
</script>

<style scoped>
  .app-container{
    min-height: calc(100vh - 50px);
    background: #E3E3E3;
  }
  .emptyGif {
    display: block;
    max-width: 100%;
    margin: auto;
  }
</style>
