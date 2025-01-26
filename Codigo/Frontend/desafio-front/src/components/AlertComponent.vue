<script setup>
const props = defineProps(['message','classAlert'])
</script>

<template>
    <div :class="classAlert" role="alert">
        <strong>Sucesso: </strong>{{ message }}
    </div>
</template>