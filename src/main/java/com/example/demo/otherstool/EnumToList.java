package com.example.demo.otherstool;

import com.example.demo.otherstool.dto.MyEnum;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName:  EnumToList   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: james
 * @date:   2018年2月25日 下午4:12:56
 */
public class EnumToList {

    public static String getText(Class<?> ref , String code) {
        String text = "";
        if (!CheckUtil.isEmpty(code)) {
            List<MyEnum> list = EnumToList.parseEnum(ref);
            for (MyEnum my : list) {
                if (code.equals(my.getIndex())) {
                    text = my.getName();
                    break;
                }
            }
        } else {
            return "empty";
        }
        return text;
    }

    public static <T> List<MyEnum> parseEnum(Class<T> ref){
        List<MyEnum> list = new ArrayList<>();
        if(ref.isEnum()){
            T[] ts = ref.getEnumConstants() ;
            for(T t : ts){
                MyEnum myEnum = new MyEnum();
                String value = getInvokeValue(t, "getName") ;
                Enum<?> tempEnum = (Enum<?>) t ;
//                if(text == null){
//                    text = tempEnum.name() ;
//                }
                String index = getInvokeValue(t, "getIndex") ;
                if(null==index){
                    index =  String.valueOf(tempEnum.ordinal()) ;
                }

                myEnum.setIndex(index);
                myEnum.setName(value);
                list.add(myEnum);
            }
        }
        return list ;
    }

    static <T> String getInvokeValue(T t , String methodName){
        Method method = MethodUtils.getAccessibleMethod( t.getClass() , methodName);
        if(null == method){
            return null ;
        }
        try {
//            String text = TransformUtils.toString(method.invoke( t )) ;
            String text = method.invoke( t ).toString() ;
            return text ;
        } catch (Exception e) {
            return null ;
        }
    }

    public static void main(String[] args) {
        //List<MyEnum> list = EnumToList.parseEnum(DesignerStatus.class);
        //System.out.println( EnumToList.parseEnum(DesignerStatus.class ).get(0).getValue() ) ;  /*获取枚举2的文本内容*/
    }
}