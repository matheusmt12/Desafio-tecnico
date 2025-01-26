<script setup>
const props = defineProps(['label','forId'])
</script>

<template>
        <div class="form-group">
            <label :for="forId">{{ label }}</label>
            <slot></slot>
        </div>

</template>