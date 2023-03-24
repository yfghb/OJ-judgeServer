## OJ-judgeServer单体判题服务

### 简介

这是一个为OnlineJudge系统准备的单体判题服务，正在开发中。



### 调用准备

- （windows）在c盘创建一个judgeServer文件夹，然后用个ide打开直接运行就可以了
- （linux）以root用户登录在“根目录/root”下创建judgeServer文件夹，使用maven打包项目然后使用java -jar命令运行项目

### post请求，参数如下

- 路径：

```json
http://主机ip:8888/judgeServer/run
```

```json
/*
(必须)systemEnv:系统环境（windows/linux）,其中windows环境只是供本地测试，代码上线后要改成linux
(必须)languege:代码的语言（目前只支持c，c++，java，python）
(必须)code:代码字符串
(必须)fileSuffix:生成该语言文件的后缀，比如如果language是java，这里就应该写.java
(必须)timeLimit:数字，单位为秒
(必须)memoryLimit:字符串，目前这个功能是没有作用的，填什么都可以
(必须)isReadFile:boolean是否读取输入输出测试点文件。统一为false，测试的时候可以为true，自己上传文件来方便测试
inputFilePath:测试点输入文件的路径，绝对路径
outputFilePath:测试点输出文件的路径，绝对路径
inputList: List<String> 测试点输入
outputList: List<String> 测试点输出
注意：
若isReadFile为false，则inputList，outputList是必须的，反之inputFilePath，outputFilePath是必须的
*/
//以下为简单a+b问题的请求判题的样例(输入两个正整数，输出这两个数的和)
//样例1
{
    "systemEnv": "windows",
    "language": "java",
    "code": "(略)",
    "fileSuffix": ".java",
    "timeLimit": 1,
    "memoryLimit": "128MB",
    "isReadFile": true,
    "inputFilePath":"C:/Users/27930/Desktop/in.txt",
    "outputFilePath":"C:/Users/27930/Desktop/out.txt"
}
//样例2
{
    "systemEnv": "windows",
    "language": "c",
    "code": "(略)",
    "fileSuffix": ".c",
    "timeLimit": 1,
    "memoryLimit": "128MB",
    "isReadFile": false,
    "inputList":["1 2","10 20"],
    "outputList":["3","30"]
}
```

### 测试点输入输出文件的书写格式

- 需要json格式，文件后缀 .txt

```json
{
	"input":["1 2","10 20"]
}
```

```json
{
	"output":["3","30"]
}
```

- 若无输入，则（假设是一个只输出hello的题目）

```json
{
	"input":["",""]
}
```

```json
{
	"output":["hello","hello"]
}
```

注意：List中每个元素都是独立的测试点



### 请求返回

```json
//caseList:测试点列表
{
    "caseList":[
        {
        "output":"3",
        "input":"1 2",
        "answer":"3",
        "compileMsg":"compile accept",
        "tag":20,
        "time":"192ms",
        "uuid":"Judgecb7c316f_1672_43bc_a2b8_d33dfd98155b"
        },
        {
        "output":"20",
        "input":"10 10",
        "answer":"20",
        "compileMsg":"compile accept",
        "tag":20,
        "time":"175ms",
        "uuid":"Judgecb7c316f_1672_43bc_a2b8_d33dfd98155b"
        },
        {
        "output":"100",
        "input":"1 99",
        "answer":"100",
        "compileMsg":"compile accept",
        "tag":20,
        "time":"206ms",
        "uuid":"Judgecb7c316f_1672_43bc_a2b8_d33dfd98155b"
        }
    ],
    "status":0
}
```

补充： uuid是随机生成的，它只在judgeServer项目里具有实际意义，在其他地方可以根据业务逻辑改成其他值。

#### 关于Status字段和Tag字段

我直接上源码

```java
public class Status {

    /** 请求格式不正确 */
    public static final Integer FORMAT_ERROR = -2;

    /** 代码通过所有测试点 */
    public static final Integer ACCEPT = 0;

    /** 代码通过部分测试点 */
    public static final Integer PARTIALLY_ACCEPT = 10;

    /** 测试点全错 */
    public static final Integer ALL_WRONG = 1;

    /** 超时 */
    public static final Integer TIME_ERROR = -999;

    /** 请求状态正常 */
    public static final Integer OK = 200;

    /** 服务器出错 */
    public static final Integer SYSTEM_ERROR = 500;

    private Status(){}
}
```

```java
public class Tag {
    /** 测试点编译失败 */
    public static final Integer COMPILE_FAIL = -100;

    /** 测试点运行失败 */
    public static final Integer RUN_FAIL = -200;

    /** 测试点不正确 */
    public static final Integer WRONG_ANSWER = -300;

    /** 测试点正确 */
    public static final Integer PASSED = 20;

    /** 测试点运行超出时间限制 */
    public static final Integer TIME_LIMIT_EXCEEDED = -400;

    /** 测试点运行超出内存限制 */
    //public static final Integer MEMORY_LIMIT_EXCEEDED = -500;

    private Tag(){}
}

```

### 其他/自定义

- 注意：在windows上的请求返回有可能会遇到乱码问题，这与不同的windows电脑的文件编码有关（utf-8/gbk）。

项目源码的实体类

```java
public class ServerRequest {
    /** 唯一uuid */
    private String uuid;

    /** 运行环境 */
    private String systemEnv;

    /** 代码的语言 */
    private String language;

    /** 代码字符串 */
    private String code;

    /** 编译命令 */
    private String[] compileCmd;

    /** 运行命令 */
    private String[] runCmd;

    /** 文件后缀名 */
    private String fileSuffix;

    /** 时间限制 */
    private Integer timeLimit;

    /** 内存限制 */
    private String memoryLimit;

    /** 是否要读取输入输出文件 */
    private Boolean isReadFile;

    /** 输入测试点 */
    private List<String> inputList;

    /** 输出测试点 */
    private List<String> outputList;

    /** 输入测试点文件路径 */
    private String inputFilePath;

    /** 输出测试点文件路径 */
    private String outputFilePath;
}

```

```java
public class ServerResponse {
    private Integer status;
    private List<TestCase> caseList;
}
```

```java
public class TestCase {
    /** 与 ServerRequest.uuid 对应 */
    private String uuid;

    /** 编译信息 */
    private String compileMsg;

    /** 测试点输入 */
    private String input;

    /** 测试点输出 */
    private String output;

    /** 用户代码的输出 */
    private String answer;

    /** 测试点状态 */
    private Integer tag;

    /** 测试点花费的时间 */
    private String time;

    /** 测试点花费的内存 */
    private String memory;

    /** 测试点运行错误的信息 */
    private String errorMsg;

}

```



