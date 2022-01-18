const phones = [
  /^(\+?213|0)(5|6|7)\d{8}$/,
  /^(!?(\+?963)|0)?9\d{8}$/,
  /^(!?(\+?966)|0)?5\d{8}$/,
  /^(\+?1)?[2-9]\d{2}[2-9](?!11)\d{6}$/,
  /^(\+?420)? ?[1-9][0-9]{2} ?[0-9]{3} ?[0-9]{3}$/,
  /^(\+?49[ ])?([(]{1}[0-9]{1,6}[)])?([0-9]{3,20})((x|ext|extension)[ ]?[0-9]{1,4})?$/,
  /^(\+?45)?(\d{8})$/,
  /^(\+?30)?(69\d{8})$/,
  /^(\+?61|0)4\d{8}$/,
  /^(\+?44|0)7\d{9}$/,
  /^(\+?852?)?[569]\d{3}?\d{4}$/,
  /^(\+?91|0)?[789]\d{9}$/,
  /^(\+?64|0)2\d{7,9}$/,
  /^(\+?27|0)\d{9}$/,
  /^(\+?26)?09[567]\d{7}$/,
  /^(\+?34)?(6\d{1}|7[1234])\d{7}$/,
  /^(\+?358|0)\s?(4(0|1|2|4|5)?|50)\s?(\d\s?){4,8}\d$/,
  /^(\+?33|0)[67]\d{8}$/,
  /^(\+972|0)([23489]|5[0248]|77)[1-9]\d{6}/,
  /^(\+?36)(20|30|70)\d{7}$/,
  /^(\+?39)?\s?3\d{2} ?\d{6,7}$/,
  /^(\+?81|0)\d{1,4}[ ]?\d{1,4}[ ]?\d{4}$/,
  /^(\+?6?01){1}(([145]{1}(|\s)?\d{7,8})|([236789]{1}(\s|)?\d{7}))$/,
  /^(\+?47)?[49]\d{7}$/,
  /^(\+?32|0)4?\d{8}$/,
  /^(\+?47)?[49]\d{7}$/,
  /^(\+?48)? ?[5-8]\d ?\d{3} ?\d{2} ?\d{2}$/,
  /^(\+?55|0)?[1-9]{2}?[2-9]{1}\d{3,4}?\d{4}$/,
  /^(\+?351)?9[1236]\d{7}$/,
  /^(\+?7|8)?9\d{9}$/,
  /^(\+3816|06)[- \d]{5,9}$/,
  /^(\+?90|0)?5\d{9}$/,
  /^(\+?84|0)?((1(2([0-9])|6([2-9])|88|99))|(9((?!5)[0-9])))([0-9]{7})$/,
  /^(\+?0?86?)?1[345789]\d{9}$/,
  /^(\+?886?|0)?9\d{8}$/
]
export function checkSpace (str) { // 首字符不能为空格
  if (str.length === 1 && str.match(/^[ ]+$/)) {
    return false
  } else {
    return true
  }
}

export function checkPhone (str) { // 国内外手机号验证
  if (str.length > 0) {
    for (let i = 0; i < phones.length; i++) {
      const reg = new RegExp(phones[i])
      if (reg.test(str)) {
        return true
      }
    }
    return false
  }
}

export function checkPoint (str) { // 有且只有一个小数点，小数点保留两位，数字位数最多五位
  const pointArr = str.split('.')
  if (pointArr.length > 2) {
    return false
  } else if (pointArr.length === 2) {
    if (str.length > 6) {
      return false
    } else {
      if (pointArr[1].length > 2) {
        return false
      }
    }
  } else {
    if (str.length > 5) {
      return false
    }
  }
  return true
}

export function checkNumber (str) { // 不能为数字
  const patrn = /^(-)?\d+(\.\d+)?$/
  if (patrn.exec(str) == null || str === '') {
    return false
  } else {
    return true
  }
}

export function checkEmail (str) { // 邮件格式
  if (!str.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/)) {
    return false
  } else {
    return true
  }
}
export function checkFax (str) { // 传真格式
  const reg = /^(\d{3,4}-)?\d{7,8}$/
  if (isNaN(str)) {
    if (reg.test(str) === false) {
      return false
    } else {
      return true
    }
  }
}
export function checkIsNumber (str) { // 是否为数字
  if (!isNaN(str)) {
    return true
  } else {
    return false
  }
}
export function checkZhengNumber (str) { // 是否为正整数
  const reg = /^([1-9]\d*|[0]{1,1})$/
  if (reg.test(str)) {
    return true
  } else {
    return false
  }
}
export function checkChinese (str) {
  const reg = /[\u4E00-\u9FA5\uF900-\uFA2D]/
  if (reg.test(str)) {
    return true
  } else {
    return false
  }
}
