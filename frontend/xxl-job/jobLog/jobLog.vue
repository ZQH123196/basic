<script setup lang="ts">

import { UrlJob, jobLogType, jobGroupType, PagesWrap } from '@/types/global';
import { TuplifyUnion } from '@/types/utils';
import { HttpTool } from '@/utils/net/http/request';
import { getCurrentInstance, onBeforeMount, onMounted, reactive, ref, toRef, watch } from 'vue';
import MyTable from '@/base-ui/table/index';
import dayjs from 'dayjs';
import SearchForm from '@/components/SearchForm/SearchForm.vue';
import { searchFormConfig } from './config/search.config';
import { ITable } from '@/base-ui/table/src/table.vue';

const instance = getCurrentInstance();
const MyTableRef = ref<InstanceType<typeof MyTable>>()

let tableConfig: ITable = reactive({
    pagination: {
        currentPage: 1,
        pageSize: 10,
        pageSizeOpt: [1, 10, 20, 50, 100, 500],
    },
    table: {
        tableHead: [
            {
                colText: "任务 ID",
                key: "jobId",
            },
            {
                colText: "执行器组",
                key: "jobGroup",
            },
            {
                colText: "调度时间",
                key: "triggerTime",
            },
            {
                colText: "调度结果",
                key: "triggerCode",
            },
            // {
            //     colText: "调度详情",
            //     key: "triggerMsg",
            // },
            {
                colText: "执行时间",
                key: "handleTime",
            },
            {
                colText: "执行备注",
                key: "handleMsg",
            }],
        tableData: [],
    }
})



let queryInfo = reactive({
    executorSelect: "all",
    missionSelect: "all",
    stateSelect: "all",
    current: tableConfig.pagination.currentPage,
    size: tableConfig.pagination.pageSize,
})

queryInfo.size = 5

let getPageData = undefined;
onMounted(async () => {
    getPageData = instance.refs['MyTableRef']?.getPageData;
    // 初始化数据
    await getPageData(queryInfo)
})

watch(() => tableConfig.pagination, () => getPageData())




const handleSearch = async () => {
    const { records: jobLogList }: { records: jobLogType[] } = await HttpTool.get(UrlJob.log_fetchListByPage)
    // ISO 8601 -> normal time
    jobLogList.forEach(jobLog => {
        jobLog.triggerTime = dayjs(jobLog.triggerTime).format("YYYY-MM-DD HH:mm:ss")
        jobLog.handleTime = dayjs(jobLog.handleTime).format("YYYY-MM-DD HH:mm:ss")
    });
    tableConfig.table.tableData = jobLogList
}


const handleResetSearch = () => {
    queryInfo = reactive({
        ...queryInfo,
        executorSelect: "all",
        missionSelect: "all",
        stateSelect: "all",
        current: 1,
    })
    tableConfig.table.tableData.length = 0;
}

</script>


<template>
    <div style="border-color: pink;
    border-style: solid;">
        <SearchForm
            @click-search-btn="handleSearch"
            @click-reset-btn="handleResetSearch"
            :search-form-config="searchFormConfig"
        ></SearchForm>

        <MyTable
            ref="MyTableRef"
            v-model:pagination="tableConfig.pagination"
            :table="tableConfig.table"
        >
        </MyTable>
    </div>
</template>


<style>
.el-table .warning-row {
    --el-table-tr-bg-color: var(--el-color-warning-lighter);
}
.el-table .success-row {
    --el-table-tr-bg-color: var(--el-color-success-lighter);
}
</style>

<style scoped>
.demo-pagination-block + .demo-pagination-block {
    margin-top: 10px;
}
.demo-pagination-block .demonstration {
    margin-bottom: 16px;
}
</style>