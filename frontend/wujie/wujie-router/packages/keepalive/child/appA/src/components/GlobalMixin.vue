<script lang="ts">
export default {
    beforeCreate() {
        console.log("app VueLifecycle beforeCreate!");
        
    },
    created() {
        console.log("app VueLifecycle created!");

    },
    mounted() {
        console.log("app VueLifecycle mounted!");

    }
};
</script>
