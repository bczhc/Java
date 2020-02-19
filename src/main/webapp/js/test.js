/**
 * 找所有位置
 * @param string 目标字符串
 * @param strToSearch 需要搜索的字符串
 * @returns {[]} 位置结果，索引
 */
function indexes(string, strToSearch) {
    let r = [];
    let len = string.length;
    for (let i = 0; i < len; i++) {
        let index = string.indexOf(strToSearch, i);
        if (index === -1) return r;
        if (index !== r[r.length - 1]) r[r.length] = index;
    }
    return r
}

console.log(indexes("iuiu123iuhiuh123uihiu123iuhiuh123", "123"));
