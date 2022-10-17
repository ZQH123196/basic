<template>
    <el-form>
        <el-form-item>
            <slot name="header"></slot>
        </el-form-item>
        <el-row>
            <!-- 遍历并表达为 table 的数据 -->
            <template v-for="(item, index) in formItems">
                <el-col
                    :span="item.span"
                    :key="index"
                >
                    <el-form-item
                        v-bind="item.content.formItemProps"
                    >
                        <!-- 检查实际的类型 -->
                        <template v-if="item.content.type == 'text'"></template>
                        <template v-if="item.content.type == 'input' || item.content.type == 'password'">
                            <el-input
                                style="width: auto;"
                                v-model="modelValue[item.content.modelKey]"
                                @change="handleModalValueUpdate(item.content.modelKey, $event)"
                                v-bind="item.content.formItemTagetProps"
                                :show-password="item.type === 'password'"
                            ></el-input>
                        </template>
                        <template v-if="item.content.type == 'select'"></template>
                        <template v-if="item.content.type == 'radio'"></template>
                        <template v-if="item.content.type == 'checkbox'"></template>
                        <template v-if="item.content.type == 'datepicker'"></template>
                    </el-form-item>
                </el-col>
            </template>
        </el-row>
        <el-form-item>
            <slot name="footer"></slot>
        </el-form-item>
    </el-form>
</template>


<script>
export default {
    name: "MyForm",
    components: {},
    model: {
        prop: "modelValue",
        event: "update:modelValue",
    },
    props: {
        modelValue: {
            type: Object,
            required: true,
        },
        formItems: {
            type: Array,
            default: [],
        },
    },
    data() {
        return {};
    },
    methods: {
        handleModalValueUpdate(value, field) {
            this.$emit("update:modelValue", {
                ...this.modelValue,
                [field]: value,
            });
        },
    },
};
</script>



<style>
</style>
