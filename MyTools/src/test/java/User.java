package test.java;

/*********************************************************
 @author guoff16201210
  * <p> 文件名称： User
  * <p> 系统名称：个人在线记账系统
  * <p> 模块名称：test.java 
  * <p> 功能说明：
  * <p> 开发人员：guoff16201210
  * <p> 开发时间：2020/4/15 下午 08:22
  * <p> 修改记录：程序版本	修改日期	修改人员	修改单号	修改说明
 *********************************************************/
public class User {

    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
