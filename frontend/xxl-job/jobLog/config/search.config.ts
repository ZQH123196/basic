import { Enum_IFormType } from '@/base-ui/form'
import { ISearchFormConfig } from '@/components/SearchForm'
import { ref } from 'vue'
import dayjs from 'dayjs';

export const searchFormConfig: ISearchFormConfig = {
  formConfig: [
    {
      span: 8,
      child: {
        type: Enum_IFormType.select,
        modelKey: "executorSelect",
        modelDefault: "all",
        formItemProps: { label: "执行器：" },
        formItemTagetProps: {
          disabled: true,
          options: [
            {
              value: 'all',
              label: '全部',
            }
          ]
        }
      }
    },
    {
      span: 8,
      child: {
        type: Enum_IFormType.select,
        modelKey: "missionSelect",
        modelDefault: "all",
        formItemProps: { label: "任务：" },
        formItemTagetProps: {
          disabled: true,
          options: [
            {
              value: 'all',
              label: '全部',
            }
          ]
        }
      }
    },
    {
      span: 8,
      child: {
        type: Enum_IFormType.select,
        modelKey: "stateSelect",
        modelDefault: "all",
        formItemProps: { label: "状态：" },
        formItemTagetProps: {
          disabled: true,
          options: [
            {
              value: 'all',
              label: '全部',
            }
          ]
        }
      }
    },
    {
      span: 16,
      child: {
        type: Enum_IFormType.datepicker,
        modelKey: "datepickerModel",
        modelDefault: [dayjs().subtract(1, "month"), dayjs()],
        formItemProps: { label: "运行日期：" },
        formItemTagetProps: {
          shortcuts: [
            {
              text: '一日内',
              value: () => {
                const start = dayjs().subtract(1, "day")
                const end = dayjs()
                return [start, end]
              },
            },
            {
              text: '一周内',
              value: () => {
                const start = dayjs().subtract(7, "day")
                const end = dayjs()
                return [start, end]
              },
            },
            {
              text: '一月内',
              value: () => {
                const start = dayjs().subtract(1, "month")
                const end = dayjs()
                return [start, end]
              },
            },
            {
              text: '一年内',
              value: () => {
                const start = dayjs().subtract(1, "year")
                const end = dayjs()
                return [start, end]
              },
            },
          ],
          type: "datetimerange",
          rangeSeparator: "至",
          startPlaceholder: "Start date",
          endPlaceholder: "End date"
        }
      }
    },
  ]
}
