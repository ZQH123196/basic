<script setup lang="ts">
import MyTable from '@/base-ui/table/index';
import { ITable } from '@/base-ui/table/src/table.vue';
import { jobInfoTableType, jobInfoType, PagesWrap, UrlJob } from '@/types/global';
import { HttpTool } from '@/utils/net/http/request';
import dayjs from 'dayjs';
import { ElMessage } from 'element-plus';
import qs from 'qs';
import { reactive, ref, watch } from 'vue';

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
                key: "id",
            }, {
                colText: "任务描述",
                key: "jobDesc",
            }, {
                colText: "调度类型",
                key: "scheduleType",
            }, {
                colText: "运行模式",
                key: "glueType",
            },
            {
                colText: "状态",
                key: "triggerStatus",
            },
            {
                colText: "操作",
                key: "extraOptional",
            },
        ],
        tableData: [],
    }
})






watch(() => MyTableProps.page, () => getPageData())
const getPageData = async () => {
    searchForm.current = MyTableProps.page.currentPage
    searchForm.size = MyTableProps.page.pageSize
    let resPageData = await HttpTool.get<PagesWrap<jobInfoType[]>>(UrlJob.info_fetchListByPage, searchForm)

    let records = resPageData.records;
    // dict -> text and pretty text
    records.forEach((it) => {
        let srcStatus = it.triggerStatus
        if (srcStatus == 0) {
            it.triggerStatus = "已停止"
        } else if (srcStatus == 1) {
            it.triggerStatus = "运行中"
        } else {
            it.triggerStatus = "未被定义的状态码！"
        }

        it.scheduleType = `${it.scheduleType}: ${it.scheduleConf}`
    })
    tableData.value = records
    // 为每行数据填上 extraOptional 以支持 table slot
    tableData.value.forEach((it) => {
    })
    dataTotal.value = resPageData.total
}
// 初始化数据
getPageData()




const handleExtraFunction = async (command: string | number | object) => {
    ElMessage(`click on item ${command}`)

    let res = await HttpTool.post<{
        code: number;
        msg: string;
        content: string;
    }>(UrlJob.info_triggerOnce,
        qs.stringify({
            id: "5",
            executorParam: "",
            addressList: ""
        }),
        undefined,
        {
            "Content-Type": "application/x-www-form-urlencoded"
        })
    const resStatus = res.code
    console.log("res", res);
    console.log("resStatus", resStatus);

    if (resStatus == 200) {
        ElMessage(`执行成功！返回码： ${resStatus}`)
    }

}

const handleNewJob = () => {
    console.log("handleNewJob");
}

</script>




<template>
    <div style="border-color: pink;
    border-style: solid;">
        <MyTable
            ref="MyTableRef"
            v-model:pagination="tableConfig.pagination"
            :table="tableConfig.table"
        >
            <template #leftHeader>
                <el-button style="margin: 10px 10px 5px 10px;" @click="handleNewJob">新增 job</el-button>
            </template>
            <template v-for="(headItem) in tableHead" :key="headItem.key" #[headItem.key]="{ row }">
                <div v-if="headItem.key == 'extraOptional'">
                    <el-dropdown
                        @command="handleExtraFunction"
                        size="small"
                        split-button
                        type="primary"
                    >
                        操作
                        <template #dropdown>
                            <el-dropdown-menu>
                                <el-dropdown-item command="runOnce">执行一次</el-dropdown-item>
                                <el-divider />
                                <el-dropdown-item command="start">启动</el-dropdown-item>
                                <el-dropdown-item command="stop">停止</el-dropdown-item>
                                <el-dropdown-item command="edit">编辑</el-dropdown-item>
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>
                </div>
                <div v-else>{{ row[headItem.key] }}</div>
            </template>
        </MyTable>
    </div>
</template>