<script setup>
const props = defineProps(['titulo'])
</script>

<template>
    <div class="card">
  <div class="card-header">
    {{ titulo }}
  </div>
  <div class="card-body">
      <slot name="conteudo"></slot>
  </div>
  <div class="card-footer">
    <slot name="footer"></slot>
  </div>
</div>
</template>