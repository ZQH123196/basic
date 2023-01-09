<template>
    <div id="search">
        <MyForm
            :modelValue.sync="modelValue"
            :formItems="formItems"
        >

            <template #header="">
                header slot
            </template>

            <template #footer="">
                footer slot
            </template>

        </MyForm>
    </div>
</template>

<script>
import MyForm from "@/basic-ui/form/index";

export default {
    name: "search",
    components: {
        MyForm,
    },
    methods: {
        test() {},
    },
    data() {
        return {
            // key: 默认值
            modelValue: {
                key: "默认值",
            },
            formItems: [
                {
                    span: 16,
                    content: {
                        type: "input",
                        // 这个 key 对应 modelValue 里面那个 key。也就是这个会作为键值来使用，比如 modelValue[modelKey] 这样。
                        modelKey: "key",
                        // 这里的其实就是 el-form-item 中的属性，v-bind 全部绑上去
                        formItemProps: {
                            label: "姓名：",
                            style: "background-color: pink;",
                        },
                        // 这里是实际双向绑定的元素的属性，比如 el-input、el-select
                        formItemTagetProps: {
                            placeholder: "请输入内容",
                            style: "",
                        },
                    },
                },
                {
                    span: 16,
                    content: {
                        type: "input",
                        // 这个 key 对应 modelValue 里面那个 key。也就是这个会作为键值来使用，比如 modelValue[modelKey] 这样。
                        modelKey: "key",
                        // 这里的其实就是 el-form-item 中的属性，v-bind 全部绑上去
                        formItemProps: {
                            label: "姓名：",
                            style: "background-color: pink;",
                        },
                        // 这里是实际双向绑定的元素的属性，比如 el-input、el-select
                        formItemTagetProps: {
                            placeholder: "请输入内容",
                            style: "",
                        },
                    },
                },
            ],
        };
    },
};
</script>

<style></style>
