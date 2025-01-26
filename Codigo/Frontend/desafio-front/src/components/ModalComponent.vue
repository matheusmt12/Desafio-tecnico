<script setup>
const props = defineProps(['titulo', 'visivel'])
</script>

<template>
    <div v-if="visivel" class="modal fade show" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true" style="display: block;">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">{{ titulo }}</h5>
                </div>
                <div class="modal-body">
                    <slot name="alert"></slot>
                    <slot name="conteudo"></slot>
                </div>
                <div class="modal-footer">
                    <slot name="footer"></slot>
                </div>
            </div>
        </div>
    </div>
</template>