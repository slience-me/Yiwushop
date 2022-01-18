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
        const time = (new Date(+new Date() + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')).substr(11, 2)
        if (parseInt(time) > 20 || parseInt(time) < 6) {
          Modal.success('亲爱的管理员，我想您可能累了，不过还是得加油哦，努力🤗🤗🤗')
        }
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
