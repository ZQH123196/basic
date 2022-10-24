<template>
    <div>
        <input
            type="checkbox"
            @input="handleChange"
            :checked="checked"
            @change="handleChange"
        />
    </div>
</template>

<script>
export default {
    model: {
        prop: "checked",
        event: "change"
    },
    props: ["checked"],
    methods: {
        handleChange(e) {
            this.$emit("change", e.target.checked);
        },
    },
};
</script>

