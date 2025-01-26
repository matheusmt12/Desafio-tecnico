<script setup>
const props = defineProps(['message','classAlert','status'])
</script>

<template>
    <div :class="classAlert" role="alert">
       <strong>{{ status }}</strong> {{ message }}
    </div>
</template>