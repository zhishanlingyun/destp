package com.destp.spring.spel;

import com.destp.common.Obj;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public class Demo {

    //表达式
    public static void demo1(){
        String exptess = "T(com.destp.spring.spel.Sku)";
        ExpressionParser parser = new SpelExpressionParser();
        Class clazz = parser.parseExpression(exptess).getValue(Class.class);
        System.out.println(clazz==Sku.class);
        String o = parser.parseExpression("T(com.destp.spring.spel.Sku).MAX").getValue(String.class);
        System.out.println(o);
        Sku sku = parser.parseExpression("new com.destp.spring.spel.Sku(1292,'苹果手机',6288)").getValue(Sku.class);
        System.out.println(sku);
        String name = parser.parseExpression("new com.destp.spring.spel.Sku(1292,'苹果手机',6288).getName()").getValue(String.class);
        System.out.println(name);
        //不可用
        /*float price = parser.parseExpression("com.destp.spring.spel.Sku s=new com.destp.spring.spel.Sku(1292,'苹果手机',6288) s.getPrice() ").getValue(Float.class);
        System.out.println(price);*/
        //ParserContext 接口定义了被解析表达式的前缀和后缀
        Sku sku1 = parser.parseExpression("#{new com.destp.spring.spel.Sku(1292,'苹果手机',6288)}",new TemplateParserContext()).getValue(Sku.class);
        System.out.println(sku1);
        Sku sku2 = parser.parseExpression("${new com.destp.spring.spel.Sku(1292,'苹果手机',6288)}",new MyParserContext()).getValue(Sku.class);
        System.out.println(sku2);
        boolean ok = parser.parseExpression("'haha' instanceof T(String)").getValue(Boolean.class);
        System.out.println(ok);

    }

    //变量及引用

    public static void demo2(){
        ExpressionParser parser = new SpelExpressionParser();
        //不为空则定义根对象
        EvaluationContext context = new StandardEvaluationContext("root value");
        context.setVariable("var1","abc");
        context.setVariable("var2","def");
        context.setVariable("var1","fff");
        String r1 = parser.parseExpression("'var1 = '+#var1+'\n'+'var2 = '+#var2").getValue(context,String.class);
        System.out.println(r1);
        //#root 代表根对象
        String root = parser.parseExpression("'root : '+#root").getValue(context,String.class);
        System.out.println(root);
        //#this 代表上下文对象
        String root1 = parser.parseExpression("'root : '+#this").getValue(context,String.class);
        System.out.println(root1);
        Sku sku = new Sku(199920,"奔驰S600",1500000);
        context.setVariable("sku",sku);
        String price = parser.parseExpression("#sku.name+' 售价 '+#sku.price").getValue(context,String.class);
        System.out.println(price);
        parser.parseExpression("#sku.price").setValue(context,5000000);
        String price1 = parser.parseExpression("#sku.getName()+' 售价 '+#sku.getPrice()").getValue(context,String.class);
        System.out.println(price1);
        String r2 = parser.parseExpression("#xxx?.ww").getValue(context,String.class);
        System.out.println(r2);

        Sku sku1 = new Sku(1928789299,"宝马X5",2000000);
        EvaluationContext context2 = new StandardEvaluationContext(sku1);
        context2.setVariable("var","skdjaflksdjf");
        String name = parser.parseExpression("#root.name").getValue(context2,String.class);
        System.out.println(name);
        String sk = parser.parseExpression("#this.toString()").getValue(context2,String.class);
        System.out.println(sk);
        String catalg = parser.parseExpression("#var").getValue(context2,String.class);
        System.out.println(catalg);

    }

    public static void main(String[] args) {
        //demo1();
        demo2();
    }

}
