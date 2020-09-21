-- 基金信息表 fund
create table fund(
    fundId              varchar2(50)            primary key             not null ,          -- 基金代码
    fundName            varchar2(50)                                    not null ,          -- 基金名称
    fundType            number(1)                                       not null ,          -- 1 - 开放式基金  2 - 封闭式基金
    managerId           varchar2(50)                                    not null ,          -- 管理人ID
    trusteeBlank        varchar2(50)                                    not null ,          -- 托管人ID
    initNetWorth        number(16,4)                                    not null ,          -- 初始净值
    sizeOfThe           number(16,4)                                    not null ,          -- 基金规模
    managerRate         number(16,4)                                    not null ,          -- 管理人费率 年利率 ？/100
    hostingRate         number(16,4)                                    not null ,          -- 托管人费率 年利率 ？/100
    provisionDays       number(1)                                       not null ,          -- 1 - 360天  2 - 365天  3 - 366天
    setUpDate           varchar2(12)                                    not null ,          -- 基金成立时间
    fundDesc            varchar2(100)                                            ,          -- 备注
    accountId           varchar2(50)                                    not null            -- 账户信息表
);

-- 券商信息表 brokers
create table brokers(
    brokersId               varchar2(20)        primary key         not null ,      -- 券商编号
    brokersName             varchar2(50)                            not null ,      -- 券商名称
    brokersInstructions     varchar2(50)                            not null ,      -- 说明
    brokersDesc             varchar2(100)                                           -- 备注
);

-- 交易席位表 seate
create table seate(
    seateId                 varchar2(50)        primary key         not null ,      -- 席位编号
    seateName               varchar2(50)                            not null ,      -- 席位名称
    seateType               number(1)                               not null ,      -- 席位类型
    seateRate               number(16,4)                            not null ,      -- 佣金费率 年利率？/100
    brokersId               varchar2(20)                            not null ,      -- 券商编号
    seateAddress            number(1)                               not null ,      -- 1=上海  2=深圳
    seateDesc               varchar2(50)                                            -- 备注
);

-- 交易所品种费率表 varietiesRate
create table varietiesRate(
    exchangeName            number(1)                               not null ,      -- 交易所名称 约束 1=“上交所” 2=“深交所”
    rateType                number(1)                               not null ,      -- 费率类型   约束1=“债券” 2=“股票”
    stampDuty               number(16,4)                            not null ,      -- 印花税
    transferFee             number(16,4)                            not null ,      -- 过户费
    collectionRate          number(16,4)                            not null ,      -- 征管费
    brokerage               number(16,4)                            not null        -- 经手费
);

-- 证券信息 securities
create  table securities(
    securitiesId            varchar2(50)        primary key         not null ,      -- 证券编号
    securitiesName          varchar2(50)                            not null ,      -- 证券名称
    securitiesType          number(1)                               not null ,      -- 证券类型 1=债券  2=股票
    issueDate               varchar2(12)                            not null ,      -- 发行日期
    delayDate               varchar2(12)                            not null ,      -- 延迟日期 T+1 ……
    stockId                 varchar2(50)                            not null ,      -- 股票板块
    exchange                number(1)                               not null ,      -- 交易所  1=上交所 2=深交所
    securitiesDesc          varchar2(50)                                            -- 备注
);

-- 债券信息表 bond
create table bond(
    securitiesId            varchar2(50)        primary key         not null ,      -- 证券编号
    bondName                varchar2(50)                            not null ,      -- 债券名称
    drawStartDate           varchar2(12)                            not null ,      -- 计息起始日期
    drawEndDate             varchar2(12)                            not null ,      -- 计息结束日期
    bondType                number(1)                               not null ,      -- 债券类型  1=银行间  0=非银行间
    parRate                 number(16,4)                            not null ,      -- 票面利率
    bondRate                number(16,4)                            not null ,      -- 债券利息
    bondRateAmount          varchar2(50)                            not null ,      -- 票面金额
    payInterestNum          number(1)                               not null ,      -- 付息次数  1=1年一次  2=1年俩次 3=1年四次
    bondDesc                varchar2(12)                                            -- 备注
);

-- 股票板块表 stock
create table stock(
    stockId             varchar2(50)        primary key         not null ,      -- 板块编号
    stockParentId       varchar2(50)                            not null ,      -- 板块父编号
    stockName           varchar2(50)                            not null ,      -- 板块名称
    stockDesc           varchar2(50)                                            -- 板块备注
);

-- 现金账户表 account
create table account(
    accountId           varchar2(50)        primary key         not null ,      -- 现金账户Id
    fundId              varchar2(50)                            not null ,      -- 基金信息表Id
    blankCardCode       varchar2(50)                            not null ,      -- 银行卡号
    accountName         varchar2(50)                            not null ,      -- 账户名称
    blankName           varchar2(50)                            not null ,      -- 银行名称
    deposit             number(1)                               not null ,      -- 存款类型 1代表活期 2代表定期
    cardRate            number(16,4)                            not null ,      -- 卡号利率 ?/100 年利率
    procisionDays       number(1)                               not null ,      -- 计息期间 1代表360  2代表365天 3代表366天
    openTime            varchar2(12)                            not null ,      -- 开户时间
    endTime             varchar2(12)                            not null ,      -- 结束时间
    accountDesc         varchar2(50)                                            -- 备注
);

-- 交易数据表 transactionData
create table transactionData(
    tradeId             varchar2(50)        primary key         not null ,      -- 交易的单子号
    dateTime            varchar2(12)                            not null ,      -- 成交日期(交易成交的日期)
    settlementDate      varchar2(12)                            not null ,      -- 结算日期(交易结算的日期)
    fundId              varchar2(50)                            not null ,      -- 基金代码来自基金表
    securitiesId        varchar2(50)                            not null ,      -- 证券编号
    brokersId           varchar2(50)                                     ,      -- 券商Id
    seateId             varchar2(50)                                     ,      -- 席位Id
    transactionDataMode        number(1)                        not null ,      -- 交易方式(1买入、2卖出，3分红，4送股)
    status              number(1)                               not null ,      -- 交易状态（0未结算，1已结算）
    price               number(16,4)                            not null ,      -- 交易价格(单价)
    num                 number(16,4)                            not null ,      -- 交易数量
    accountId           varchar2(50)                            not null ,      -- 现金账户Id
    netReceipts         number(16,4)                            not null ,      -- 实收金额
    totalSum            number(16,4)                            not null ,      -- 交易总金额
    flag                number(1)                               not null ,      -- 交易标识,1流入，-1流出
    commission          number(16,4)                            not null ,      -- 佣金费用（券商）
    transfer            number(16,4)                            not null ,      -- 过户费（交易所）
    brokerage           number(16,4)                            not null ,      -- 经手费（交易所）
    stamp               number(16,4)                            not null ,      -- 印花税（上交国家的税）
    management          number(16,4)                            not null ,      -- 征管费（上交国家的税）
    security            number(16,4)                            not null ,      -- 证券利息
    transactionDataDesc       varchar2(50)                                      -- 备注
);

-- 净值统计表 valueStatistics
create table valueStatistics(
    valueStatisticsDate         varchar2(12)        primary key         not null ,      -- 净值统计日期
    valueStatisticsId           varchar2(50)                            not null ,      -- 库存编号
    projectId                   varchar2(50)                            not null ,      -- 项目编号
    projectName                 varchar2(50)                            not null ,      -- 项目名称
    projectCode                 varchar2(50)                            not null ,      -- 项目代码/账户号
    quantity                    number(12)                              not null ,      -- 股数
    peice                       number(16,4)                            not null ,      -- 行情
    cost                        number(16,4)                            not null ,      -- 成本
    marketValue                 number(16,4)                            not null ,      -- 市值
    valuation                   number(16,4)                            not null ,      -- 估值增值
    projectFatherId             varchar2(50)                            not null ,      -- 父项目编号
    relation                    varchar2(50)                            not null ,      -- 项目关系
    appreciationSum             number(16,4)                            not null ,      -- 合计估值增值
    liabilities                 number(16,4)                            not null ,      -- 总负债
    propertySum                 number(16,4)                            not null ,      -- 资产合计
    propertyNetWorth            number(16,4)                            not null ,      -- 资产净值
    unitNetWorth                number(16,4)                            not null        -- 单位净值
);

-- 证券应收应付表 securitiesClosedPay
create table securitiesClosedPay(
    securitiesClosedPayId       varchar2(20)            primary key         not null ,      -- 证券应收应付Id
    fundId                      varchar2(50)                                not null ,      -- 基金信息表Id
    accountId                   varchar2(50)                                not null ,      -- 账户信息表ID
    securitiesId                varchar2(50)                                not null ,      -- 证券信息表ID
    serviceType                 number(1)                                   not null ,      -- 业务类型 1=清算款 2=估值增值 3=债券利息
    amount                      number(16,4)                                not null ,      -- 金额
    dateTime                    varchar2(12)                                not null ,      -- 日期
    flag                        number(1)                                   not null        -- 流入1 流出-1
);


-- 15. 现金应收应付表
create table cashClosedPay(
    cashClosedPayId             varchar2(50)                                not null , -- 现金应收应付Id  pk主键
    fundId                      Varchar2(50)                                not null , -- FK 基金信息表Id  fund表
    accountId                   Varchar2(50)                                not null , -- FK 账户信息表ID  account表
    securitiesId                Varchar2(50)                                not null , -- FK 证券信息表ID  securities表
    serviceType                 number(1)                                   not null , -- 业务类型 1=“管理费”2=“托管费”3=“存款利息”4=“申购赎回款”
    amount                      number(16,2)                                not null , -- 金额
    dateTime                    varchar2(10)                                not null , -- 日期
    flag                        number(1)                                              -- 资金流向 1=“流入”-1 =“流出”
);



/*16. 行情数据表 market*/
create table market(
    marketId                    Varchar2(50)                                not null ,               /*该表的ID*/
    securitiesId                Varchar2(50)                                not null ,               /*证券信息表ID，引用证券编号 securitiesId，证券名称 securitiesName*/
    dateTime                    varchar2(10)                                not null ,               /*录入证券的日期*/
    openPrice                   Number(16,2)                                not null ,               /*当天单份证券的开盘价格*/
    closingPrice                Number(16,2)                                not null ,               /*交易所闭市之后的价格*/
    marketDesc                  Varchar2(50)                                                        /* 行情数据的其他信息*/
);


/*17. TA交易数据表 taTransaction*/
create table taTransaction(
    transactionId               varchar2(50)                                not null ,             /*PK , 交易数据编号 TA202008310001*/
    dateTime                    varchar2(10)                                not null ,             /*交易日期*/
    balanceDate                 varchar2(10)                                not null ,             /*结算日期*/
    fundId                      varchar2(50)                                not null ,             /*FK 基金Id来自基金表*/
    fundNum                     Number(16,2)                                not null ,             /*交易数量*/
    accountId                   varchar2(50)                                not null ,             /* FK 来自现金账户表 现金账户Id*/
    totalMoney                  Number(16,2)                                not null ,             /*总金额*/
    actualMoney                 Number(16,2)                                not null ,             /*实际交易金额*/
    price                       number(16,2)                                not null ,             /*单价(昨日单位净值)*/
    cost                        Number(16,2)                                not null ,             /*费用*/
    agencies                    Number(1)                                   not null ,             /*代销机构1=建设银行  2=工商银行  3=农业银行*/
    transactionType	            Number(1)                                   not null ,             /* 1认购 2申购 3赎回*/
    transactionStatus           Number(1)                                   not null               /*1结算 0未结算*/
);



/*18. 证券库存表 securitiesInventory*/
create table securitiesInventory(
    securitiesInventoryId       Varchar2(50)                                not null ,                      /*Varchar2(50)	PK 证券库存ID*/
    dateTime                    varchar2(10)                                not null ,                      /*证券库存日期*/
    securitiesId                Varchar2(50)                                not null ,                      /* 证券信息表ID，引用证券编号securitiesId，证券名securitiesName*/
    fundId                      Varchar2(50)                                not null ,                      /*基金表Id 印入基金代码*/
    securityPeriodFlag          Number(16,2)                                not null ,                      /* 是否从其他系统导入的期初数据  0：不是  1：是*/
    securitiesNum               Number(16,2)                                not null ,                      /*计算总金额条件 证券的数量*/
    price                       Number(16,2)                                not null ,                      /*计算总金额条件 单位成本*/
    total                       Number(16,2)                                not null ,                      /*证券数量*单位成本*/
    securitiesInventoryDesc     Varchar(50)			                                                        /*备注*/
);

/*19. 现金库存 cashInventory*/
create table cashInventory(
    cashInventoryId             Varchar2(50)                                not null ,               /*主键现金库存 C202008310001*/
    fundId	                    Varchar2(50)                                not null ,                       /* FK 基金Id来自基金表*/
    cashBlance 	                Number(16,4)                                not null ,                   /*现金余额*/
    accountId	                Varchar2(50)                                not null ,                   /* FK 现金账户Id来自现金账户表*/
    dateTime                    varchar2(10)                                not null ,                      /*统计日期*/
    securitiesNum	            Number(16,2)                                not null ,               /* 证券数量*/
    securityPeriodFlag          Number(16,2)                                not null ,            /*是否从其他系统导入的期初数据  0：不是  1：是*/
    cashInventoryDesc           Varchar2(50)                                                     /*备注*/
);



/*20. TA库存表 taInventory*/
create table taInventory(
    taInventoryId	            Varchar2(50)                                not null ,           /*TA库存Id*/
    fundId	                    Varchar2(50)                                not null ,                   /* FK 基金Id来自基金表*/
    tanum	                    Number(16,4)                                not null ,                   /*Ta数量*/
    tatotal	                    Number(16,4)                                not null ,                   /*现金余额*/
    dateTime	                varchar2(10)                                not null ,               /*统计日期*/
    securityPeriodFlag          Number(16,4)                                not null ,        /*是否从其他系统导入的期初数据  0：不是  1：是*/
    taInventorydesc	            Varchar2(50)                    /*备注*/
);



/*21.  托管人表 trustee*/
create table trustee(
    trusteeId	                varchar2(50)                                not null ,           /*PK  托管人的ID*/
    trusteeName	                Varchar2(50)                                not null ,           /*托管人名字*/
    trusteeAddres	            Varchar2(100)                                not null ,      /*托管人地址*/
    trusteeCompany	            Varchar2(50)                                not null ,       /*托管公司*/
    trusteePhone	            Varchar2(50)                                not null ,       /*电话*/
    trusteeFee	                number(16,2)                                not null ,           /*托管费率*/
    trusteeDesc	                Varchar2(100)               /*备注*/
);



/*22.  管理人表 manager*/
create table manager(
    managerId	                varchar2(50)                                not null ,           /* PK  管理人的ID*/
    managerAge	                number(3)                                   not null ,              /*托管人地址*/
    managerCompany	            varchar2(100)                               not null ,      /*所在公司*/
    managerPhone	            varchar2(50)                                not null ,       /*管理人的电话*/
    managerFee	                number(16,2)                                not null ,           /*管理费率*/
    managerDesc	                Varchar2(100)               /*备注*/
);


/*23. 资金调拨表 bankTreasurer*/
create table bankTreasurer(
    treasurerId	                varchar2(50)                            not null ,           /*资金调拨表Id*/
    fundId  	                varchar2(50)                            not null ,           /*基金编号*/
    totalPrice	                number(16,2)                            not null ,           /*调拨总数额*/
    accountId	                varchar2(50)                            not null ,           /*FK 现金账户表Id  account表*/
    flag 	                    number(2)                               not null ,                  /*调拨方向 1代表流入 -1代表流出*/
    dbTime	                    varchar2(10)                            not null ,               /*调拨日期*/
    dateTime                    varchar2(10)                            not null ,           /*业务日期*/
    businessId	                varchar2(50)                            not null ,          /*业务标号*/
    allocatingType	            number(2)                               not null ,          /*调拨类型 1代表存款利息 2代表申购赎回清算款 3代表买卖交易清算款 4代表债券利息*/
    bankTreasurerDesc	        varchar2(200)               /*备注*/
);



/*24. 权益数据设置表 equityData*/
create table equityData(
    dateTime	                varchar2(10)                            not null ,           /*业务日期*/
    securityId	                varchar2(20)                            not null ,             /*证券ID*/
    securityName	            varchar2(50)                            not null ,       /*证券名称*/
    equitiesRecord	            varchar2(10)                            not null ,       /*权益登记日 除权日-1day*/
    equitiesExright	            varchar2(10)                            not null ,       /*权益除权日 yyyy-MM-dd*/
    receivedDate	            varchar2(10)                            not null ,       /*到账日期 除权日+2day*/
    equitiesType	            number(2)                               not null ,          /*权益类型  1.送股  2.分红*/
    proportion	                number(5)                               not null ,              /*比例   单位为%来计算*/
    disposeStatus               number(10)                              not null          /*处理状态处理状态分为已处理和未处理与权益处理的已结算和未结算相对应（0未处理 1已处理）*/
);



/*26. 现金应收应付库存表  cashClosedPayInventory*/
create table cashClosedPayInventory(
    crcsId	                    varchar2(50)                            not null ,               /*现金应收应付库存编号*/
    businessDate	            varchar2(10)                            not null ,       /*业务日期*/
    cashAccountName	            varchar2(50)                            not null ,       /*现金账户名称*/
    businessType	            number(2)                               not null ,          /*业务类型  1.管理费 2.托管费  3.存款利息  4.申购赎回费*/
    businessStatus	            number(2)                               not null ,          /*业务状态 1.流入  -1流出*/
    initialSigns	            number(2)                               not null ,          /*期初标志 1.是   0.否*/
    totalMoney	                number(15)                              not null              /*总金额*/
);


/*27. 证券应收应付库存表 securitiesClosedPayInventory*/
create table securitiesClosedPayInventory(
    nsrcsId	                    varchar2(50)                            not null ,               /*证券存库Id 主键*/
    datetime	                varchar2(10)                            not null ,           /*业务日期*/
    fundId	                    varchar2(50)                            not null ,               /*FK 基金信息表Id      fund表*/
    securitiesId	            varchar2(50)                            not null ,          /*FK 证券信息表ID  securities表*/
    securitiesType	            Number(1)                               not null ,          /*证券应收应付类型 1=估值款 2=证券清算款 3=债券利息*/
    flag	                    varchar2(20)                            not null ,                  /*业务日期*/
    tootaIPrice	                Number(16,4)                            not null ,           /*总金额*/
    securitiesClosedPayDesc	    varchar2(50),               /*备注*/
    securityPeriodFlag	        Number(1)                               not null       /*期初标志 是否从其他系统导入得期初数据 0：不是 1：是*/
);



/*28.存款业务表 deposit*/
create table deposit(
    depositId	                varchar2(50)                            not null ,           /* 存款业务Id   流水*/
    fundId	                    varchar2(50)                            not null ,               /* 基金Id*/
    outAccountId	            varchar2(50)                            not null ,       /*流出现金账户*/
    inAccountId	                varchar2(50)                            not null ,           /* 流入现金账户*/
    directionOfMoney	        number(2)                               not null ,      /*资金调拨方向  1代表流入 -1代表流出*/
    businessDate	            varchar2(10)                            not null ,       /*业务时间*/
    businessType	            number(2)                               not null ,          /*业务类型 1代表定期三天 2代表七天 3代表活期*/
    money                       number(16,2)                            not null ,                 /*存款金额*/
    interest                    number(16,2)                            not null ,              /*所含利息*/
    endDate	                    varchar2(10)                            not null ,               /*存款业务到期时间*/
    flag	                    number(2)                               not null ,                  /*到期办理标志   0未办理 1已办理*/
    depositDesc	                varchar2(200)               /*备注*/
);



/*29. 划款指令表  transferMoney*/
create table transferMoney(
    transferMoneyId	            varchar2(50)                            not null ,               /*划款指令Id*/
    inAccountId 	            varchar2(50)                            not null ,               /*划款到的账户  （下拉表格  现金账户表）*/
    inBlankName 	            varchar2(50)                            not null ,               /*划款到的账户的开户银行*/
    crossSectionDate	        varchar2(10)                            not null ,           /*划款时间 not null*/
    accountingDate              varchar2(10)                            not null ,           /*到账时间 not null*/
    money 	                    number(16,2)                            not null ,                       /*划款金额*/
    outAccount  	            varchar2(50)                            not null ,               /*划款的账户  （下拉表格  现金账户表）*/
    outBlankName 	            varchar2(50)                            not null ,               /*划款的账户的开户银行*/
    foundId         	        varchar2(50)                            not null ,           /*FK 基金信息表Id  fund表*/
    purpose	                    varchar2(150)                       /*划款的用途*/
);



/*30. 用户信息表 userInfo*/
create  table  userInfo(
    userId	                    number(3)                                  not null ,                          /*用户信息Id  pk主键*/
    userName	                varchar2(50)                         not null ,                   /*用户名称*/
    userPwd	                    varchar2(50)                         not null ,                       /*用户密码*/
    roleId	                    number(3)                            not null ,                       /*FK 角色编号roleId （角色表的ID）*/
    status	                    number(1)                            not null ,                          /*角色状态（0未启用，1已启用）*/
    userInfoDesc	            varchar2(50)                        /*备注*/
);



/*31. 角色信息表 role*/
create table role(
    roleId	                    Number(3)                               not null ,                          /*角色信息Id  pk主键*/
    roleName	                Varchar2(50)                            not null ,                   /*角色名称*/
    roleDesc	                varchar2(50)                        /*备注*/
);










-- 插入数据

-- 基金信息表 fund
insert into fund values ('289289289','一度教育基金',1,'100000','000001',1000000000,5000000000,3,2,2,'20200831','投资有风险，交易需谨慎','1176040487');

-- 券商信息表 brokers
insert into brokers values ('01010001','上海马自达','钱来前来','财源滚滚');

-- 交易席位表 seate
insert into seate values ('11001','正大光正',1,3,'01010001',1,'我是正席');

-- 交易所品种费率表 varietiesRate
insert into varietiesRate values (1,1,20,30,50,40);

-- 证券信息 securities
insert into securities values ('600988','天美集团',1,'20200822','20200823','001',1,'好汽油石化造');

-- 债券信息表 bond
insert into bond values ('600988','一度债券','20200822','20200911',1,4,3,'100',2,'欠债还钱');


-- 股票板块表 stock
insert into stock values ('001','000','石油化工','石油化工类');
insert into stock values ('002','001','汽油','汽油类');

-- 现金账户表 account
insert into account values ('1176040487','289289289','6217995588007428073','一度教育','建设银行',1,2,3,'20200822','20210822','我是一度账户');

-- 交易数据表 transactionData
insert into transactionData values ('T2020083100001','20200902','20200903','289289289','600988','01010001','11001',1,0,120,1000,'1176040487',120000,120000,-1,20,23,50,60,60,1200,'赚钱真难');

-- 净值统计表 valueStatistics
insert into valueStatistics values ('20200901','0501001','00201','正太几天','1176040487',1000,102,100000,1010200,10200,'02000121','同类',1010200,0,1010200,1010200,3);

-- 证券应收应付表 securitiesClosedPay
insert into securitiesClosedPay values ('10010120','289289289','1176040487','600988',1,5000,'20200903',1);


insert into market values ('10001','289289289','2020-9-1',101,102,'上涨空间很大');


insert into cashClosedPay values ('123654789','289289289','1176040487','600988',1,300,'2020-9-1',-1);


insert into taTransaction values ('TA202008310001','2020-9-1','2020-9-2','289289289',100,'1176040487',100000,9000,102,50,1,2,0);


insert into securitiesInventory values ('10001','2020-9-1','600988','289289289',0,10000,105,102,'暂无');


insert into cashInventory values ('C202008310001','289289289',10000000,'1176040487','2020-9-1',10000,0,'暂无');


insert into taInventory values ('10001','289289289',100000,10000000,'2020-9-1',0,'暂无');


insert into trustee values ('10001','招商银行','湖南省长沙市','招商银行股份有限公司','123654789',0.00012,'暂无');


insert into manager values ('10001',001,'一度基金有限公司','07364802802',0.01,'暂无');


insert into bankTreasurer values ('10001','289289289',100000,'1176040487',-1,'2020-9-1','2020-9-1','10001',3,'暂无');


insert into equityData values ('2020-9-1','600988','天美集团','2020-9-1','2020-9-2','2020-9-4',1,0.05,0);


insert into cashClosedPayInventory values ('123654789','2020-9-1','一度教育',1,-1,1,100000);


insert into securitiesClosedPayInventory values ('10001','2020-9-1','289289289','600988',3,'2020-9-1',10000,'暂无',0);


insert into deposit values ('10001','289289289','963258','1176040487',1,'2020-9-1',2,1000000,2000,'2020-9-8',0,'暂无');


insert into transferMoney values ('10001','7412589632','建设银行','2020-9-1','2020-9-6',1000000,'7412589632','招商银行','289289289','定期存款');

insert into userInfo values (1,'admin','123',1,1,'金融估值核算系统超级管理员账户');
insert into userInfo values (2,'张三','123',2,1,'金融估值核算系统普通账户');


insert into role values (1,'管理员','超级管理员账户');
insert into role values (2,'财务','财务人员');
insert into role values (3,'普通','普通人员');
