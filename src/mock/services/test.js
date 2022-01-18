import Mock from 'mockjs2'
import { builder, getQueryParameters } from '../util'

const totalCount = 5701

const stuList = (options) => {
  const parameters = getQueryParameters(options)

  const result = []
  const pageNo = parseInt(parameters.page)
  const pageSize = parseInt(parameters.rows)
  const totalPage = Math.ceil(totalCount / pageSize)
  const key = (pageNo - 1) * pageSize
  const next = (pageNo >= totalPage ? (totalCount % pageSize) : pageSize) + 1

  for (let i = 1; i < next; i++) {
    const tmpKey = key + i
    result.push({
      key: tmpKey,
      id: tmpKey,
      name: '学生名字' + tmpKey,
      no: '学号' + tmpKey,
      class: '班级' + tmpKey,
      sex: Mock.mock('@integer(0, 1)'),
      spe: '专业' + tmpKey,
      dep: '系部' + tmpKey
    })
  }

  return builder({
    pageSize: pageSize,
    pageNum: pageNo,
    total: totalCount,
    totalPage: totalPage,
    list: result
  })
}

const teaList = (options) => {
  const parameters = getQueryParameters(options)

  const result = []
  const pageNo = parseInt(parameters.page)
  const pageSize = parseInt(parameters.rows)
  const totalPage = Math.ceil(totalCount / pageSize)
  const key = (pageNo - 1) * pageSize
  const next = (pageNo >= totalPage ? (totalCount % pageSize) : pageSize) + 1

  for (let i = 1; i < next; i++) {
    const tmpKey = key + i
    result.push({
      key: tmpKey,
      id: tmpKey,
      name: '教师名字' + tmpKey,
      no: '工号' + tmpKey,
      class: '班级' + tmpKey,
      sex: Mock.mock('@integer(0, 1)'),
      spe: '专业' + tmpKey,
      dep: '系部' + tmpKey
    })
  }

  return builder({
    pageSize: pageSize,
    pageNum: pageNo,
    total: totalCount,
    totalPage: totalPage,
    list: result
  })
}

const depList = (options) => {
  const parameters = getQueryParameters(options)

  const result = []
  const pageNo = parseInt(parameters.page)
  const pageSize = parseInt(parameters.rows)
  const totalPage = Math.ceil(totalCount / pageSize)
  const key = (pageNo - 1) * pageSize
  const next = (pageNo >= totalPage ? (totalCount % pageSize) : pageSize) + 1

  for (let i = 1; i < next; i++) {
    const tmpKey = key + i
    result.push({
      key: tmpKey,
      id: tmpKey,
      name: '系部名称' + tmpKey
    })
  }

  return builder({
    pageSize: pageSize,
    pageNum: pageNo,
    total: totalCount,
    totalPage: totalPage,
    list: result
  })
}

const speList = (options) => {
  const parameters = getQueryParameters(options)

  const result = []
  const pageNo = parseInt(parameters.page)
  const pageSize = parseInt(parameters.rows)
  const totalPage = Math.ceil(totalCount / pageSize)
  const key = (pageNo - 1) * pageSize
  const next = (pageNo >= totalPage ? (totalCount % pageSize) : pageSize) + 1

  for (let i = 1; i < next; i++) {
    const tmpKey = key + i
    result.push({
      key: tmpKey,
      id: tmpKey,
      dep: '系部名称' + tmpKey,
      spe: '专业名称' + tmpKey
    })
  }

  return builder({
    pageSize: pageSize,
    pageNum: pageNo,
    total: totalCount,
    totalPage: totalPage,
    list: result
  })
}

const classList = (options) => {
  const parameters = getQueryParameters(options)

  const result = []
  const pageNo = parseInt(parameters.page)
  const pageSize = parseInt(parameters.rows)
  const totalPage = Math.ceil(totalCount / pageSize)
  const key = (pageNo - 1) * pageSize
  const next = (pageNo >= totalPage ? (totalCount % pageSize) : pageSize) + 1

  for (let i = 1; i < next; i++) {
    const tmpKey = key + i
    result.push({
      key: tmpKey,
      id: tmpKey,
      dep: '系部名称' + tmpKey,
      spe: '专业名称' + tmpKey,
      class: '班级名称' + tmpKey
    })
  }

  return builder({
    pageSize: pageSize,
    pageNum: pageNo,
    total: totalCount,
    totalPage: totalPage,
    list: result
  })
}

const stuDataList = (options) => {
  const parameters = getQueryParameters(options)

  const result = []
  const pageNo = parseInt(parameters.page)
  const pageSize = parseInt(parameters.rows)
  const totalPage = Math.ceil(totalCount / pageSize)
  const key = (pageNo - 1) * pageSize
  const next = (pageNo >= totalPage ? (totalCount % pageSize) : pageSize) + 1

  for (let i = 1; i < next; i++) {
    const tmpKey = key + i
    result.push({
      key: tmpKey,
      id: tmpKey,
      name: '学生' + tmpKey,
      no: '学号' + tmpKey,
      class: '班级' + tmpKey,
      spe: '专业' + tmpKey,
      dep: '系部' + tmpKey,
      receive: Mock.mock('@integer(0, 10000)'),
      complete: Mock.mock('@integer(0, 10000)')
    })
  }

  return builder({
    pageSize: pageSize,
    pageNum: pageNo,
    total: totalCount,
    totalPage: totalPage,
    list: result
  })
}

const classDataList = (options) => {
  const parameters = getQueryParameters(options)

  const result = []
  const pageNo = parseInt(parameters.page)
  const pageSize = parseInt(parameters.rows)
  const totalPage = Math.ceil(totalCount / pageSize)
  const key = (pageNo - 1) * pageSize
  const next = (pageNo >= totalPage ? (totalCount % pageSize) : pageSize) + 1

  for (let i = 1; i < next; i++) {
    const tmpKey = key + i
    result.push({
      key: tmpKey,
      id: tmpKey,
      class: '班级' + tmpKey,
      spe: '专业' + tmpKey,
      dep: '系部' + tmpKey,
      receive: Mock.mock('@integer(0, 10000)'),
      complete: Mock.mock('@integer(0, 10000)')
    })
  }

  return builder({
    pageSize: pageSize,
    pageNum: pageNo,
    total: totalCount,
    totalPage: totalPage,
    list: result
  })
}

const speDataList = (options) => {
  const parameters = getQueryParameters(options)

  const result = []
  const pageNo = parseInt(parameters.page)
  const pageSize = parseInt(parameters.rows)
  const totalPage = Math.ceil(totalCount / pageSize)
  const key = (pageNo - 1) * pageSize
  const next = (pageNo >= totalPage ? (totalCount % pageSize) : pageSize) + 1

  for (let i = 1; i < next; i++) {
    const tmpKey = key + i
    result.push({
      key: tmpKey,
      id: tmpKey,
      spe: '专业' + tmpKey,
      dep: '系部' + tmpKey,
      receive: Mock.mock('@integer(0, 10000)'),
      complete: Mock.mock('@integer(0, 10000)')
    })
  }

  return builder({
    pageSize: pageSize,
    pageNum: pageNo,
    total: totalCount,
    totalPage: totalPage,
    list: result
  })
}

const depDataList = (options) => {
  const parameters = getQueryParameters(options)

  const result = []
  const pageNo = parseInt(parameters.page)
  const pageSize = parseInt(parameters.rows)
  const totalPage = Math.ceil(totalCount / pageSize)
  const key = (pageNo - 1) * pageSize
  const next = (pageNo >= totalPage ? (totalCount % pageSize) : pageSize) + 1

  for (let i = 1; i < next; i++) {
    const tmpKey = key + i
    result.push({
      key: tmpKey,
      id: tmpKey,
      dep: '系部' + tmpKey,
      receive: Mock.mock('@integer(0, 10000)'),
      complete: Mock.mock('@integer(0, 10000)')
    })
  }

  return builder({
    pageSize: pageSize,
    pageNum: pageNo,
    total: totalCount,
    totalPage: totalPage,
    list: result
  })
}

const teaDataList = (options) => {
  const parameters = getQueryParameters(options)

  const result = []
  const pageNo = parseInt(parameters.page)
  const pageSize = parseInt(parameters.rows)
  const totalPage = Math.ceil(totalCount / pageSize)
  const key = (pageNo - 1) * pageSize
  const next = (pageNo >= totalPage ? (totalCount % pageSize) : pageSize) + 1

  for (let i = 1; i < next; i++) {
    const tmpKey = key + i
    result.push({
      key: tmpKey,
      id: tmpKey,
      name: '教师' + tmpKey,
      no: '工号' + tmpKey,
      class: '班级' + tmpKey,
      spe: '专业' + tmpKey,
      dep: '系部' + tmpKey,
      receive: Mock.mock('@integer(0, 10000)'),
      complete: Mock.mock('@integer(0, 10000)')
    })
  }

  return builder({
    pageSize: pageSize,
    pageNum: pageNo,
    total: totalCount,
    totalPage: totalPage,
    list: result
  })
}

const adminList = (options) => {
  const parameters = getQueryParameters(options)

  const result = []
  const pageNo = parseInt(parameters.page)
  const pageSize = parseInt(parameters.rows)
  const totalPage = Math.ceil(totalCount / pageSize)
  const key = (pageNo - 1) * pageSize
  const next = (pageNo >= totalPage ? (totalCount % pageSize) : pageSize) + 1

  for (let i = 1; i < next; i++) {
    const tmpKey = key + i
    result.push({
      key: tmpKey,
      id: tmpKey,
      name: '姓名' + tmpKey,
      no: Mock.mock('@integer(10000000000, 19999999999)'),
      role: '角色' + tmpKey
    })
  }

  return builder({
    pageSize: pageSize,
    pageNum: pageNo,
    total: totalCount,
    totalPage: totalPage,
    list: result
  })
}

const roleList = (options) => {
  const parameters = getQueryParameters(options)

  const result = []
  const pageNo = parseInt(parameters.page)
  const pageSize = parseInt(parameters.rows)
  const totalPage = Math.ceil(totalCount / pageSize)
  const key = (pageNo - 1) * pageSize
  const next = (pageNo >= totalPage ? (totalCount % pageSize) : pageSize) + 1

  for (let i = 1; i < next; i++) {
    const tmpKey = key + i
    result.push({
      key: tmpKey,
      id: tmpKey,
      name: '角色名称' + tmpKey,
      menus: '都是权限'
    })
  }

  return builder({
    pageSize: pageSize,
    pageNum: pageNo,
    total: totalCount,
    totalPage: totalPage,
    list: result
  })
}

const drugDatabaseList = (options) => {
  const parameters = getQueryParameters(options)

  // const result = []
  const pageNo = parseInt(parameters.page)
  const pageSize = parseInt(parameters.rows)
  const totalPage = Math.ceil(9 / pageSize)
  // const key = (pageNo - 1) * pageSize
  // const next = (pageNo >= totalPage ? (totalCount % pageSize) : pageSize) + 1

  // for (let i = 1; i < next; i++) {
  //   const tmpKey = key + i
  //   result.push({
  //     key: tmpKey,
  //     id: tmpKey,
  //     name: '药材名称' + tmpKey,
  //     url: 'https://boradcast.oss-cn-beijing.aliyuncs.com/5502B92C255D5CE8C9DDCB5956BFA1EE.jpeg',
  //     type: '分类',
  //     from: '来源',
  //     spe: '性状特征',
  //     smile: '性味与归经',
  //     func: '功能与主治',
  //     method: '用法用量',
  //     attention: '使用注意',
  //     made: '炮制',
  //     speValue: '特征值',
  //     speImageNum: '特征图片量',
  //     qrcode: ''
  //   })
  // }

  const result = [
    {
      key: 0,
      id: 0,
      name: '人参',
      url: '/img/renshen.jpg',
      type: '药材',
      from: '本品为五加科植物人参PanaxginsengC.A.Mey.的干燥根和根茎。多于秋季采挖，洗净经晒干或烘干。栽培的俗称“园参”；播种在山林野生状态下自然生长的称“林下山参”，习称“籽海”。',
      spe: '主根呈纺锤形或圆柱形，长3～15cm，直径1～2cm。表面灰黄色，上部或全体有疏浅断续的粗横纹及明显的纵皱，下部有支根2～3条，并着生多数细长的须根，须根上常有不明显的细小疣状突出。根茎（芦头）长1～4cm，直径0.3～1.5cm，多拘挛而弯曲，具不定根（艼）和稀疏的凹窝状茎痕（芦碗）。质较硬，断面淡黄白色，显粉性，形成层环纹棕黄色，皮部有黄棕色的点状树脂道及放射状裂隙。香气特异，味微苦、甘。\n' +
        '    或主根多与根茎近等长或较短，呈圆柱形、菱角形或人字形，长1～6cm。表面灰黄色，具纵皱纹，上部或中下部有环纹，支根多为2～3条，须根少而细长，清晰不乱，有较明显的疣状突起。根茎细长，少数粗短，中上部具稀疏或密集而深陷的茎痕。不定根较细，多下垂。',
      smile: '甘、微苦，微温。归脾、肺、心、肾经。',
      func: '大补元气，复脉固脱，补脾益肺，生津养血，安神益智。用于体虚欲脱，肢冷脉微，脾虚食少，肺虚喘咳，津伤口渴，内热消渴，气血亏虚，久病虚羸，惊悸失眠，阳痿宫冷。',
      method: '3～9g，另煎兑服；也可研粉吞服，一次2g，一日2次',
      attention: '不宜与藜芦、五灵脂同用。',
      made: '原药簸净杂质，置锅内炒至深黄色，微有香味，取出。放凉。',
      speValue: '已有',
      speImageNum: 1,
      qrcode: ''
    },
    {
      key: 1,
      id: 1,
      name: '八角',
      url: '/img/bajiao.jpg',
      type: '药材',
      from: '本品为木兰科植物八角茴香Illicium verum Hookf.的干燥成熟果实。秋、冬二季果实由绿变黄时采摘，置沸水中略烫后干燥或直接干燥。',
      spe: '本品为聚合果，多由8个蓇葖果组成，放射状排列于中轴上。蓇葖果长1～2cm，宽0.3～0.5cm，高0.6～lcm；外表面红棕色，有不规则皱纹，顶端呈鸟喙状，上侧多开裂；内表面淡棕色，平滑，有光泽；质硬而脆。果梗长3～4cm，连于果实基部中央，弯曲，常脱落。每个蓇葖果含种子1粒，扁卵圆形，长约6mm，红棕色或黄棕色，光亮，尖端有种脐；胚乳白色，富油性。气芳香，味辛、甜。',
      smile: '辛，温。归肝、肾、脾、胃经。',
      func: '温阳散寒，理气止痛。用于寒疝腹痛，肾虚腰痛，胃寒呕吐，脘腹冷痛。\n',
      method: '3～6g。',
      attention: '不宜与藜芦、五灵脂同用。',
      made: '原药簸净杂质，置锅内炒至深黄色，微有香味，取出。放凉。',
      speValue: '已有',
      speImageNum: 1,
      qrcode: ''
    },
    {
      key: 2,
      id: 2,
      name: '白芥子',
      url: '/img/baijiezi.jpg',
      type: '药材',
      from: '白芥子，别名辣菜子，拉丁文名Semen sinapis.一年或二年生植物白芥 Sinapis alba L. 的干燥成熟种子。十字花科，呈球形，表面灰白色至淡黄色，种皮薄而脆，有油性。无臭，味辛辣。可入药。主要产自安徽、河南、四川、山东等地。全国各地多有栽培。',
      spe: '一年生或二年生草本，高30～100厘米。茎直立，多分枝，幼枝被微毛，老枝光滑，有时微被白粉。基生叶大，呈琴状分裂，先端裂片特别长大，两侧裂片甚小；茎上的叶不分裂，披针形至线形。总状花序多数，具成圆锥状；花萼4，绿色；花瓣4，略向外展，呈十字形，鲜黄色；雄蕊6，4强；子房长圆形。长角果光滑无毛，无明显的喙。花期4～6月。果期5～8月。',
      smile: '酸、涩，寒。归肺、脾、肝、大肠经。\n',
      func: '温肺豁痰利气，乳腺散结通络止痛。用于寒痰喘咳，胸胁胀痛，痰滞经络，关节麻木、疼痛，痰湿流注，阴疽肿毒。',
      method: '3～9克。外用适量。',
      attention: '不宜与藜芦、五灵脂同用。',
      made: '原药簸净杂质，置锅内炒至深黄色，微有香味，取出。放凉。',
      speValue: '已有',
      speImageNum: 1,
      qrcode: ''
    },
    {
      key: 3,
      id: 3,
      name: '灵芝',
      url: '/img/lingzhi.jpg',
      type: '药材',
      from: '一年生直立草本，有香气，茎绿色，圆角四棱形，多分枝，除基部外，密生细长白毛。先端急尖或渐尖，基部圆形或宽楔形，边缘有粗锯齿，两面均绿色而具毛，下面稍苍淡且有腺点。药用主治咳逆，痰喘，气滞便秘等疾病。',
      spe: '果实卵形或略呈三角形圆锥体状，长径2.5～3.5mm，短径2～2.5mm。表面灰白色至黄白色，有隆起的网纹。果皮质脆，易压碎。种仁黄白色，富油质。气微香，嚼之有油腻感。',
      smile: '苦、甘、涩，微寒。归肺、肝、胃经。',
      func: '降气化痰，止咳平喘。',
      method: '口服。水蜜丸每次6g，大蜜丸每次1丸；日2次。',
      attention: '不宜与藜芦、五灵脂同用。',
      made: '原药簸净杂质，置锅内炒至深黄色，微有香味，取出。放凉。',
      speValue: '已有',
      speImageNum: 1,
      qrcode: ''
    },
    {
      key: 4,
      id: 4,
      name: '使君子',
      url: '/img/shijunzi.jpg',
      type: '药材',
      from: '本品为使君子科植物使君子Quisqualis indica L. 的干燥成熟果实。秋季果皮变紫黑色时采收，除去杂质，干燥。',
      spe: '本品呈椭圆形或卵圆形，具5条纵棱，偶有4～9棱，长2.5～4cm，直径约2cm。表面黑褐色至紫黑色，平滑，微具光泽。顶端狭尖，基部钝圆，有明显圆形的果梗痕。质坚硬，横切面多呈五角星形，棱角处壳较厚，中间呈类圆形空腔。种子长椭圆形或纺锤形，长约2cm，直径约1cm；表面棕褐色或黑褐色，有多数纵皱纹；种皮薄，易剥离；子叶2，黄白色，有油性，断面有裂隙。气微香，味微甜。',
      smile: '甘，温。归脾、胃经。',
      func: '杀虫消积。用于蛔虫病，蛲虫病，虫积腹痛，小儿疳积。',
      method: '使君子9～12g，捣碎入煎剂；使君子仁6～9g，多入丸散或单用，作1～2次分服。小儿每岁1～1.5粒，炒香嚼服，1日总量不超过20粒。',
      attention: '服药时忌饮浓茶。',
      made: '使君子  除去杂质。用时捣碎。',
      speValue: '已有',
      speImageNum: 1,
      qrcode: ''
    },
    {
      key: 5,
      id: 5,
      name: '冬虫夏草',
      url: '/img/dongchongxiacao.jpg',
      type: '药材',
      from: '中药名。又称为猫爪簕、南蛇簕、老鸦枕头等。为豆科植物南蛇竻的种子。该植物的根（南蛇竻根）、苗（南蛇竻苗）亦供药用性苦寒，有散瘀，止痛，清热，去湿的功效。',
      spe: '有刺藤本，全株被短柔毛。花期3～4月。果期5～9月。8～9月份采成熟果实，取出种子，晒干。干燥种子椭圆形或长圆形，两端钝圆，长约1.2～2.2厘米，直径约0.7～1.2厘米，外面黑褐色或暗棕色，光滑，有的具细密的环状横纹或横裂纹，基部有珠柄残基，旁有小圆形的合点。质坚硬，不易破开。 常生于山沟中空旷的溪旁、路边或灌木丛中。',
      smile: '归心、脾、肾经。',
      func: '1、根、茎、叶：适用于感冒发热，风湿痹痛，外用于跌打损伤。骨折疮疡肿毒，皮肤瘙痒，毒蛇咬伤。\n' +
        '2、种仁：用于急性腹泻，痢疾，膀胱炎。',
      method: '根、茎、叶、种仁：9～15g；\n' +
        '茎、叶：外用适量，鲜品捣烂敷患处。',
      attention: '服药时忌饮浓茶。',
      made: '8～9月份采成熟果实，取出种子，晒干。',
      speValue: '已有',
      speImageNum: 1,
      qrcode: ''
    },
    {
      key: 6,
      id: 6,
      name: '山药',
      url: '/img/shanyao.jpg',
      type: '药材',
      from: '本品为薯蓣科植物薯蓣Dioscotea opposita Thunb.的干燥根茎。冬季茎叶枯萎后采挖，切去根头，洗净，除去外皮和须根，干燥，习称“毛山药片[1]”；或除去外皮，趁鲜切厚片，干燥，称为“山药片”；也有选择肥大顺直的干燥山药，置清水中，浸至无干心，闷透，切齐两端，用木板搓成圆柱状，晒干，打光，习称“光山药”。',
      spe: '毛山药 本品略呈圆柱形，弯曲而稍扁，长15～30cm，直径1.5～6cm。表面黄白色或淡黄色，有纵沟、纵皱纹及须根痕，偶有浅棕色外皮残留。体重，质坚实，不易折断，断面白色，粉性。气微，味淡、微酸，嚼之发黏。\n' +
        '    山药片 为不规则的厚片，皱缩不平，切面白色或黄白色，质坚脆，粉性。气微，味淡、微酸。\n' +
        '    光山药呈圆柱形，两端平齐，长9～18cm，直径1.5～3cm。表面光滑，白色或黄白色。',
      smile: '甘，平。归脾、肺、肾经。',
      func: '补脾养胃，生津益肺，补肾涩精。用于脾虚食少，久泻不止，肺虚喘咳，肾虚遗精，带下，尿频，虚热消渴。麸炒山药补脾健胃。用于脾虚食少，泄泻便溏，白带过多。',
      method: '15～30g。',
      attention: '服药时忌饮浓茶。',
      made: '山药 取毛山药或光山药除去杂质，分开大小个，泡润至透，切厚片，干燥。切片者呈类圆形的厚片。表面类白色或淡黄白色，质脆，易折断，切面类白色，富粉性。',
      speValue: '已有',
      speImageNum: 1,
      qrcode: ''
    },
    {
      key: 7,
      id: 7,
      name: '红枣',
      url: '/img/hongzao.jpg',
      type: '药材',
      from: '红枣，鼠李科枣属植物，成熟后变为红色。常晒干制成枣干。它的维生素含量非常高，有“天然维生素丸”的美誉，具有滋阴补阳的功效。红枣作为滋补佳品，素有“日食三枣，长生不老”之说。',
      spe: '鼠李科枣属植物，成熟后变为红色。常晒干制成枣干。',
      smile: '红枣味甘性温、归脾胃经',
      func: '有补中益气、养血安神、缓和药性的功能',
      method: '15～30g。',
      attention: '不宜与藜芦、五灵脂同用。',
      made: '红枣的食用方法有很多，蒸、炖、煨、煮均可。最常用的方法是将红枣煎水服用，这样既不会影响保肝的药效，也可避免生吃所引起的腹泻。将10～30克红枣洗净，并用小刀在其表皮划出直纹来帮助养分溢出，然后加适量的水煮1小时左右即可。也可以在粥里加入红枣，做成枣粥食用。另外，将红枣加工制作成红枣莲子汤、红枣花生汤等，也是比较常见的方法。',
      speValue: '已有',
      speImageNum: 1,
      qrcode: ''
    },
    {
      key: 8,
      id: 8,
      name: '红花',
      url: '/img/honghua.jpg',
      type: '药材',
      from: '本品为菊科植物红花 Carthamus tinctorius L.的干燥花。夏季花由黄变红时采摘，阴干或晒干。',
      spe: '本品为不带子房的管状花，长1～2cm。表面红黄色或红色。花冠筒细长，先端5裂，裂片呈狭条形，长5～8mm；雄蕊5，花药聚合成筒状，黄白色；柱头长圆柱形，顶端微分叉。质柔软。气微香，味微苦。',
      smile: '辛，温。归心、肝经。',
      func: '活血通经，散瘀止痛。用于经闭，痛经，恶露不行，癥瘕痞块，胸痹心痛，瘀滞腹痛，胸胁刺痛，跌扑损伤，疮疡肿痛。',
      method: '3～10g。',
      attention: '孕妇慎用。',
      made: '8～9月份采成熟果实，取出种子，晒干。',
      speValue: '已有',
      speImageNum: 1,
      qrcode: ''
    }
  ]

  return builder({
    pageSize: pageSize,
    pageNum: pageNo,
    total: 9,
    totalPage: totalPage,
    list: result
  })
}

const drugTrainList = (options) => {
  const parameters = getQueryParameters(options)

  // const result = []
  const pageNo = parseInt(parameters.page)
  const pageSize = parseInt(parameters.rows)
  const totalPage = Math.ceil(totalCount / pageSize)
  // const key = (pageNo - 1) * pageSize
  // const next = (pageNo >= totalPage ? (totalCount % pageSize) : pageSize) + 1
  //
  // for (let i = 1; i < next; i++) {
  //   const tmpKey = key + i
  //   result.push({
  //     key: tmpKey,
  //     id: tmpKey,
  //     name: '药材名称' + tmpKey,
  //     type: '分类',
  //     url: 'https://boradcast.oss-cn-beijing.aliyuncs.com/5502B92C255D5CE8C9DDCB5956BFA1EE.jpeg',
  //     speImageNum: Mock.mock('@integer(1, 1000)'),
  //     status: Mock.mock('@integer(0, 1)')
  //   })
  // }

  const result = [
    {
      key: 0,
      id: 0,
      name: '人参',
      url: '/img/renshen.jpg',
      type: '药材',
      from: '本品为五加科植物人参PanaxginsengC.A.Mey.的干燥根和根茎。多于秋季采挖，洗净经晒干或烘干。栽培的俗称“园参”；播种在山林野生状态下自然生长的称“林下山参”，习称“籽海”。',
      spe: '主根呈纺锤形或圆柱形，长3～15cm，直径1～2cm。表面灰黄色，上部或全体有疏浅断续的粗横纹及明显的纵皱，下部有支根2～3条，并着生多数细长的须根，须根上常有不明显的细小疣状突出。根茎（芦头）长1～4cm，直径0.3～1.5cm，多拘挛而弯曲，具不定根（艼）和稀疏的凹窝状茎痕（芦碗）。质较硬，断面淡黄白色，显粉性，形成层环纹棕黄色，皮部有黄棕色的点状树脂道及放射状裂隙。香气特异，味微苦、甘。\n' +
        '    或主根多与根茎近等长或较短，呈圆柱形、菱角形或人字形，长1～6cm。表面灰黄色，具纵皱纹，上部或中下部有环纹，支根多为2～3条，须根少而细长，清晰不乱，有较明显的疣状突起。根茎细长，少数粗短，中上部具稀疏或密集而深陷的茎痕。不定根较细，多下垂。',
      smile: '甘、微苦，微温。归脾、肺、心、肾经。',
      func: '大补元气，复脉固脱，补脾益肺，生津养血，安神益智。用于体虚欲脱，肢冷脉微，脾虚食少，肺虚喘咳，津伤口渴，内热消渴，气血亏虚，久病虚羸，惊悸失眠，阳痿宫冷。',
      method: '3～9g，另煎兑服；也可研粉吞服，一次2g，一日2次',
      attention: '不宜与藜芦、五灵脂同用。',
      made: '原药簸净杂质，置锅内炒至深黄色，微有香味，取出。放凉。',
      speValue: '已有',
      qrcode: '',
      speImageNum: Mock.mock('@integer(1, 1000)'),
      status: Mock.mock('@integer(0, 1)')
    },
    {
      key: 1,
      id: 1,
      name: '八角',
      url: '/img/bajiao.jpg',
      type: '药材',
      from: '本品为木兰科植物八角茴香Illicium verum Hookf.的干燥成熟果实。秋、冬二季果实由绿变黄时采摘，置沸水中略烫后干燥或直接干燥。',
      spe: '本品为聚合果，多由8个蓇葖果组成，放射状排列于中轴上。蓇葖果长1～2cm，宽0.3～0.5cm，高0.6～lcm；外表面红棕色，有不规则皱纹，顶端呈鸟喙状，上侧多开裂；内表面淡棕色，平滑，有光泽；质硬而脆。果梗长3～4cm，连于果实基部中央，弯曲，常脱落。每个蓇葖果含种子1粒，扁卵圆形，长约6mm，红棕色或黄棕色，光亮，尖端有种脐；胚乳白色，富油性。气芳香，味辛、甜。',
      smile: '辛，温。归肝、肾、脾、胃经。',
      func: '温阳散寒，理气止痛。用于寒疝腹痛，肾虚腰痛，胃寒呕吐，脘腹冷痛。\n',
      method: '3～6g。',
      attention: '不宜与藜芦、五灵脂同用。',
      made: '原药簸净杂质，置锅内炒至深黄色，微有香味，取出。放凉。',
      speValue: '已有',
      qrcode: '',
      speImageNum: Mock.mock('@integer(1, 1000)'),
      status: Mock.mock('@integer(0, 1)')
    },
    {
      key: 2,
      id: 2,
      name: '白芥子',
      url: '/img/baijiezi.jpg',
      type: '药材',
      from: '白芥子，别名辣菜子，拉丁文名Semen sinapis.一年或二年生植物白芥 Sinapis alba L. 的干燥成熟种子。十字花科，呈球形，表面灰白色至淡黄色，种皮薄而脆，有油性。无臭，味辛辣。可入药。主要产自安徽、河南、四川、山东等地。全国各地多有栽培。',
      spe: '一年生或二年生草本，高30～100厘米。茎直立，多分枝，幼枝被微毛，老枝光滑，有时微被白粉。基生叶大，呈琴状分裂，先端裂片特别长大，两侧裂片甚小；茎上的叶不分裂，披针形至线形。总状花序多数，具成圆锥状；花萼4，绿色；花瓣4，略向外展，呈十字形，鲜黄色；雄蕊6，4强；子房长圆形。长角果光滑无毛，无明显的喙。花期4～6月。果期5～8月。',
      smile: '酸、涩，寒。归肺、脾、肝、大肠经。\n',
      func: '温肺豁痰利气，乳腺散结通络止痛。用于寒痰喘咳，胸胁胀痛，痰滞经络，关节麻木、疼痛，痰湿流注，阴疽肿毒。',
      method: '3～9克。外用适量。',
      attention: '不宜与藜芦、五灵脂同用。',
      made: '原药簸净杂质，置锅内炒至深黄色，微有香味，取出。放凉。',
      speValue: '已有',
      qrcode: '',
      speImageNum: Mock.mock('@integer(1, 1000)'),
      status: Mock.mock('@integer(0, 1)')
    },
    {
      key: 3,
      id: 3,
      name: '灵芝',
      url: '/img/lingzhi.jpg',
      type: '药材',
      from: '一年生直立草本，有香气，茎绿色，圆角四棱形，多分枝，除基部外，密生细长白毛。先端急尖或渐尖，基部圆形或宽楔形，边缘有粗锯齿，两面均绿色而具毛，下面稍苍淡且有腺点。药用主治咳逆，痰喘，气滞便秘等疾病。',
      spe: '果实卵形或略呈三角形圆锥体状，长径2.5～3.5mm，短径2～2.5mm。表面灰白色至黄白色，有隆起的网纹。果皮质脆，易压碎。种仁黄白色，富油质。气微香，嚼之有油腻感。',
      smile: '苦、甘、涩，微寒。归肺、肝、胃经。',
      func: '降气化痰，止咳平喘。',
      method: '口服。水蜜丸每次6g，大蜜丸每次1丸；日2次。',
      attention: '不宜与藜芦、五灵脂同用。',
      made: '原药簸净杂质，置锅内炒至深黄色，微有香味，取出。放凉。',
      speValue: '已有',
      qrcode: '',
      speImageNum: Mock.mock('@integer(1, 1000)'),
      status: Mock.mock('@integer(0, 1)')
    },
    {
      key: 4,
      id: 4,
      name: '使君子',
      url: '/img/shijunzi.jpg',
      type: '药材',
      from: '本品为使君子科植物使君子Quisqualis indica L. 的干燥成熟果实。秋季果皮变紫黑色时采收，除去杂质，干燥。',
      spe: '本品呈椭圆形或卵圆形，具5条纵棱，偶有4～9棱，长2.5～4cm，直径约2cm。表面黑褐色至紫黑色，平滑，微具光泽。顶端狭尖，基部钝圆，有明显圆形的果梗痕。质坚硬，横切面多呈五角星形，棱角处壳较厚，中间呈类圆形空腔。种子长椭圆形或纺锤形，长约2cm，直径约1cm；表面棕褐色或黑褐色，有多数纵皱纹；种皮薄，易剥离；子叶2，黄白色，有油性，断面有裂隙。气微香，味微甜。',
      smile: '甘，温。归脾、胃经。',
      func: '杀虫消积。用于蛔虫病，蛲虫病，虫积腹痛，小儿疳积。',
      method: '使君子9～12g，捣碎入煎剂；使君子仁6～9g，多入丸散或单用，作1～2次分服。小儿每岁1～1.5粒，炒香嚼服，1日总量不超过20粒。',
      attention: '服药时忌饮浓茶。',
      made: '使君子  除去杂质。用时捣碎。',
      speValue: '已有',
      qrcode: '',
      speImageNum: Mock.mock('@integer(1, 1000)'),
      status: Mock.mock('@integer(0, 1)')
    },
    {
      key: 5,
      id: 5,
      name: '冬虫夏草',
      url: '/img/dongchongxiacao.jpg',
      type: '药材',
      from: '中药名。又称为猫爪簕、南蛇簕、老鸦枕头等。为豆科植物南蛇竻的种子。该植物的根（南蛇竻根）、苗（南蛇竻苗）亦供药用性苦寒，有散瘀，止痛，清热，去湿的功效。',
      spe: '有刺藤本，全株被短柔毛。花期3～4月。果期5～9月。8～9月份采成熟果实，取出种子，晒干。干燥种子椭圆形或长圆形，两端钝圆，长约1.2～2.2厘米，直径约0.7～1.2厘米，外面黑褐色或暗棕色，光滑，有的具细密的环状横纹或横裂纹，基部有珠柄残基，旁有小圆形的合点。质坚硬，不易破开。 常生于山沟中空旷的溪旁、路边或灌木丛中。',
      smile: '归心、脾、肾经。',
      func: '1、根、茎、叶：适用于感冒发热，风湿痹痛，外用于跌打损伤。骨折疮疡肿毒，皮肤瘙痒，毒蛇咬伤。\n' +
        '2、种仁：用于急性腹泻，痢疾，膀胱炎。',
      method: '根、茎、叶、种仁：9～15g；\n' +
        '茎、叶：外用适量，鲜品捣烂敷患处。',
      attention: '服药时忌饮浓茶。',
      made: '8～9月份采成熟果实，取出种子，晒干。',
      speValue: '已有',
      qrcode: '',
      speImageNum: Mock.mock('@integer(1, 1000)'),
      status: Mock.mock('@integer(0, 1)')
    },
    {
      key: 6,
      id: 6,
      name: '山药',
      url: '/img/shanyao.jpg',
      type: '药材',
      from: '本品为薯蓣科植物薯蓣Dioscotea opposita Thunb.的干燥根茎。冬季茎叶枯萎后采挖，切去根头，洗净，除去外皮和须根，干燥，习称“毛山药片[1]”；或除去外皮，趁鲜切厚片，干燥，称为“山药片”；也有选择肥大顺直的干燥山药，置清水中，浸至无干心，闷透，切齐两端，用木板搓成圆柱状，晒干，打光，习称“光山药”。',
      spe: '毛山药 本品略呈圆柱形，弯曲而稍扁，长15～30cm，直径1.5～6cm。表面黄白色或淡黄色，有纵沟、纵皱纹及须根痕，偶有浅棕色外皮残留。体重，质坚实，不易折断，断面白色，粉性。气微，味淡、微酸，嚼之发黏。\n' +
        '    山药片 为不规则的厚片，皱缩不平，切面白色或黄白色，质坚脆，粉性。气微，味淡、微酸。\n' +
        '    光山药呈圆柱形，两端平齐，长9～18cm，直径1.5～3cm。表面光滑，白色或黄白色。',
      smile: '甘，平。归脾、肺、肾经。',
      func: '补脾养胃，生津益肺，补肾涩精。用于脾虚食少，久泻不止，肺虚喘咳，肾虚遗精，带下，尿频，虚热消渴。麸炒山药补脾健胃。用于脾虚食少，泄泻便溏，白带过多。',
      method: '15～30g。',
      attention: '服药时忌饮浓茶。',
      made: '山药 取毛山药或光山药除去杂质，分开大小个，泡润至透，切厚片，干燥。切片者呈类圆形的厚片。表面类白色或淡黄白色，质脆，易折断，切面类白色，富粉性。',
      speValue: '已有',
      qrcode: '',
      speImageNum: Mock.mock('@integer(1, 1000)'),
      status: Mock.mock('@integer(0, 1)')
    },
    {
      key: 7,
      id: 7,
      name: '红枣',
      url: '/img/hongzao.jpg',
      type: '药材',
      from: '红枣，鼠李科枣属植物，成熟后变为红色。常晒干制成枣干。它的维生素含量非常高，有“天然维生素丸”的美誉，具有滋阴补阳的功效。红枣作为滋补佳品，素有“日食三枣，长生不老”之说。',
      spe: '鼠李科枣属植物，成熟后变为红色。常晒干制成枣干。',
      smile: '红枣味甘性温、归脾胃经',
      func: '有补中益气、养血安神、缓和药性的功能',
      method: '15～30g。',
      attention: '不宜与藜芦、五灵脂同用。',
      made: '红枣的食用方法有很多，蒸、炖、煨、煮均可。最常用的方法是将红枣煎水服用，这样既不会影响保肝的药效，也可避免生吃所引起的腹泻。将10～30克红枣洗净，并用小刀在其表皮划出直纹来帮助养分溢出，然后加适量的水煮1小时左右即可。也可以在粥里加入红枣，做成枣粥食用。另外，将红枣加工制作成红枣莲子汤、红枣花生汤等，也是比较常见的方法。',
      speValue: '已有',
      qrcode: '',
      speImageNum: Mock.mock('@integer(1, 1000)'),
      status: Mock.mock('@integer(0, 1)')
    },
    {
      key: 8,
      id: 8,
      name: '红花',
      url: '/img/honghua.jpg',
      type: '药材',
      from: '本品为菊科植物红花 Carthamus tinctorius L.的干燥花。夏季花由黄变红时采摘，阴干或晒干。',
      spe: '本品为不带子房的管状花，长1～2cm。表面红黄色或红色。花冠筒细长，先端5裂，裂片呈狭条形，长5～8mm；雄蕊5，花药聚合成筒状，黄白色；柱头长圆柱形，顶端微分叉。质柔软。气微香，味微苦。',
      smile: '辛，温。归心、肝经。',
      func: '活血通经，散瘀止痛。用于经闭，痛经，恶露不行，癥瘕痞块，胸痹心痛，瘀滞腹痛，胸胁刺痛，跌扑损伤，疮疡肿痛。',
      method: '3～10g。',
      attention: '孕妇慎用。',
      made: '8～9月份采成熟果实，取出种子，晒干。',
      speValue: '已有',
      qrcode: '',
      speImageNum: Mock.mock('@integer(1, 1000)'),
      status: Mock.mock('@integer(0, 1)')
    }
  ]

  return builder({
    pageSize: pageSize,
    pageNum: pageNo,
    total: 9,
    totalPage: totalPage,
    list: result
  })
}

const drugTypeList = (options) => {
  const parameters = getQueryParameters(options)

  // const result = []
  const pageNo = parseInt(parameters.page)
  const pageSize = parseInt(parameters.rows)
  const totalPage = Math.ceil(1 / pageSize)
  // const key = (pageNo - 1) * pageSize
  // const next = (pageNo >= totalPage ? (totalCount % pageSize) : pageSize) + 1
  //
  // for (let i = 1; i < next; i++) {
  //   const tmpKey = key + i
  //   result.push({
  //     key: tmpKey,
  //     id: tmpKey,
  //     name: '分类名称' + tmpKey
  //   })
  // }

  const result = [
    {
        key: 0,
        id: 0,
        name: '药材'
    }
  ]

  return builder({
    pageSize: pageSize,
    pageNum: pageNo,
    total: 1,
    totalPage: totalPage,
    list: result
  })
}

Mock.mock(/\/stu/, 'get', stuList)
Mock.mock(/\/tea/, 'get', teaList)
Mock.mock(/\/dep/, 'get', depList)
Mock.mock(/\/spe/, 'get', speList)
Mock.mock(/\/class/, 'get', classList)
Mock.mock(/\/dataStu/, 'get', stuDataList)
Mock.mock(/\/dataClass/, 'get', classDataList)
Mock.mock(/\/dataSpe/, 'get', speDataList)
Mock.mock(/\/dataDep/, 'get', depDataList)
Mock.mock(/\/dataTea/, 'get', teaDataList)
Mock.mock(/\/admin/, 'get', adminList)
Mock.mock(/\/role/, 'get', roleList)
Mock.mock(/\/drugDatabase/, 'get', drugDatabaseList)
Mock.mock(/\/drugTrain/, 'get', drugTrainList)
Mock.mock(/\/drugType/, 'get', drugTypeList)
