import { PagesWrap, jobLogType, UrlJob } from "@/types/global";
import { HttpTool } from "@/utils/net/http/request";
import { onMounted, ref } from "vue";

const usePageData = async (searchForm: any) => {
    let resPageData = await HttpTool.get<PagesWrap<jobLogType[]>>(UrlJob.log_fetchListByPage, searchForm)
    const records = resPageData.records;
    return {
        records,
        total: resPageData.total,
    }
}
onMounted(() => {
    handleCurrentChange(currentPage.value);
})