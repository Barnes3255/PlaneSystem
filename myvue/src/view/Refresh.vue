<!-- 空页面，负责中转到目标页面 -->
<template>
  <div></div>
</template>

<script>
export default {
  name: 'refresh',
  data () {
    return {
    }
  },
  beforeRouteEnter (to, from, next) {
    next(vm => {
      vm.$router.replace(from.path)
    })
  }
}

</script>
<style scoped>
</style>
