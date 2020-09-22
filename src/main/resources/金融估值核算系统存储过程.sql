-- 金融估值核算系统的存储过程
create or replace package pkg_tables
is
    -- 创建查询的存储过程
    -- p_tableName  表名（一张表直接传表名，多张表自己拼接sql）
    -- 括号一定要带 例如：（select * from dept join EMP E on DEPT.DEPTNO = E.DEPTNO）
    -- p_condition  查询的条件（自己拼接过来）
    -- p_pageSize 每页显示的条数
    -- p_page  查询的页面
    -- p_count 总条数（输出参数）
    -- p_cursor 返回的游标 （输出参数）
    procedure sp_select(p_tableName varchar2, p_condition varchar2, p_pageSize number, p_page number,p_count out number,p_cursor out sys_refcursor);

    -- 获取最大的id编号的存储过程
    -- p_tableName 表名
    -- p_acronym 表名的简写
    -- p_date  当前日期
    -- p_tableId 返回的id
    procedure sp_selectMaxId(p_tableName varchar2,p_acronym varchar2,p_date varchar2,p_tableId out varchar2);
end pkg_tables;


create or replace package body pkg_tables
is
    --创建查询的存储过程
    procedure sp_select(p_tableName varchar2, p_condition varchar2, p_pageSize number, p_page number,p_count out number,p_cursor out sys_refcursor)
    is
        v_sql varchar2(4000):='select * from (select rownum as rn,d.* from(select * from '|| p_tableName || ' where 1=1';
        v_sql2 varchar2(4000) := 'select count(*) from (select * from '|| p_tableName || ' where 1=1 ';
    begin
        if p_condition is not null or p_condition !='' then
            v_sql := v_sql || p_condition;
            v_sql2 := v_sql2 || p_condition;
        end if;
        v_sql := v_sql || ') d where rownum<='|| p_page*p_pageSize ||') where rn>'|| (p_page-1)*p_pageSize ;
        v_sql2 := v_sql2 || ')';
        open p_cursor for v_sql;
        EXECUTE IMMEDIATE v_sql2 INTO p_count;
    end sp_select;


    -- 获取最大的id编号
    procedure sp_selectMaxId(p_tableName varchar2,p_acronym varchar2,p_date varchar2,p_tableId out varchar2)
    is
        v_sql varchar2(4000) := 'select '|| p_tableName || 'Id from ' || p_tableName || ' where '|| p_tableName || 'Id like '''||p_acronym || p_date ||'%''';
        v_cursor sys_refcursor;
        -- 定义行变量（只接收一个列  id列）
        v_row varchar2(50);
        -- 定义一个变量用来接收最大的编号
        v_maxNumber varchar2(50) :='00000';
        -- 定义一个变量用来接收剪切的编号
        v_number varchar2(50);
        -- 定义一个变量用来接收最终的编号
        v_endNumber varchar2(50);

    begin
        open v_cursor for v_sql;
        loop
            fetch v_cursor into v_row;
            exit when v_cursor%notfound;
            -- 对查询的id进行剪切 获取后缀编号 赋值给v_number
            SELECT SUBSTR(v_row, -5, 5) into v_number FROM dual;
            -- 循环进行比较 把最大的赋值给v_maxNumber
            if v_maxNumber < v_number then
                v_maxNumber:=v_number;
            end if;
           /* DBMS_OUTPUT.PUT_LINE(v_row);
            DBMS_OUTPUT.PUT_LINE(v_number);*/
         end loop;
        -- 在原有的最大的id基础上+1
        v_endNumber:= v_maxNumber + 1;
       /* DBMS_OUTPUT.PUT_LINE(v_maxNumber);
        DBMS_OUTPUT.PUT_LINE(v_maxNumber + 1);
        DBMS_OUTPUT.PUT_LINE(lengthb(v_endNumber));*/
        -- 判断v_endNumber的长度 进行拼接
        if length(v_endNumber) =1 then
            v_endNumber:= '0000' || v_endNumber;
            elsif length(v_endNumber) =2 then
                v_endNumber:= '000' || v_endNumber;
            elsif length(v_endNumber) =3 then
                v_endNumber:= '00' || v_endNumber;
            elsif length(v_endNumber) =4 then
                v_endNumber:= '0' || v_endNumber;
        end if;
        -- 拼接最终的tableId
        p_tableId := p_acronym || p_date || v_endNumber;
    end sp_selectMaxId;

end pkg_tables;