# set -v 

echo "输出行号, $0 代表输入数据（整行），$n	当前记录的第n个字段，字段间由 FS分隔。NR -> number of row"
cat demo_往某位置插入某值.sh | awk 'NR==1{print NR, $0;}'
cat demo_往某位置插入某值.sh | awk 'NR>1{print NR, $0;}'

# echo "往某位置插入某值"
# echo "a b f g" | awk '{$2=$2" c d e"; print;}'

# echo "格式化空白串"
# # 随便取一个位置赋值，触发 awk 根据 OFS 值进行文档重建
# cat ./不规则文本串 | awk 'BEGIN{OFS="\t"}{$1=$1; print;}'


