



以下为一对 sm2 的实例：

```js
// @ts-ignore
import {sm2} from 'sm-crypto';


// 公钥
const publicKey = "0430756f05b5e8dea62e044e7b6e61d0de8d16acc97bbc23a12506c3cb28536597f3cc59de599dcc117cf8299955287f79cd09ac60291e070a69e4b271c546817f" 

//sm2的加解密时有两种方式即0——C1C2C3、1——C1C3C2，前后端需要统一
const cipherMode = 1
let encryptPwd = sm2.doEncrypt('123', publicKey, cipherMode) // 加密
// 注意后端 Java 使用 bc 库解密的话，需要在密文串前面加上 04
encryptPwd = "04" + encryptPwd
console.log("encryptPwd", encryptPwd);

```


```groovy
import cn.hutool.core.codec.Base64
import cn.hutool.core.util.HexUtil
import cn.hutool.crypto.BCUtil
import cn.hutool.crypto.SmUtil
import cn.hutool.crypto.asymmetric.KeyType
import cn.hutool.crypto.asymmetric.SM2
import org.bouncycastle.crypto.engines.SM2Engine
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey



String SM2_PRIVATE_KEY = "00e6f9cd3dbe9d827c5b0949dc5ab9247543b7ff6c249207b5eb0765d78be84d2d";
String SM2_PUBLIC_KEY = "0430756f05b5e8dea62e044e7b6e61d0de8d16acc97bbc23a12506c3cb28536597f3cc59de599dcc117cf8299955287f79cd09ac60291e070a69e4b271c546817f";

SM2 sm2 = SmUtil.sm2(SM2_PRIVATE_KEY, SM2_PUBLIC_KEY);
sm2.setMode(SM2Engine.Mode.C1C3C2)
// bc 解密 sm2，密文前面要加 04
String txtHex = "04"+"af9466b9e1d31f83c5d8bd0d4db486eb1ecb845add2f265f9062e733857652db4cdd3d1dd35a29acc91d6ea4dda819e8377b669f74b6806b2cafa454fd11f7a657afe21af93da6f5d1af2fe5c94dcaf2f420cb660385ce64374af4529daa7c9a35ba01"
println(sm2.decryptStr(txtHex, KeyType.PrivateKey))

println("done!")
```












SM4:

注意前后端是否需要 hex 的处理
在这里，前端往往直接使用 hex 的 key，而后端则需求 hex decode 之后的 key。

```groovy
import cn.hutool.core.codec.Base64
import cn.hutool.core.util.HexUtil
import cn.hutool.crypto.BCUtil
import cn.hutool.crypto.SmUtil
import cn.hutool.crypto.asymmetric.KeyType
import cn.hutool.crypto.asymmetric.SM2
import org.bouncycastle.crypto.engines.SM2Engine
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey

import java.nio.charset.Charset

println(HexUtil.encodeHexStr("1008610086100861", Charset.forName("utf-8")))
println(HexUtil.decodeHex("31303038363130303836313030383631").size())

String sm4KeyHex = "31303038363130303836313030383631"
String data = "667c1c69f37d15e51254e6b7455ff51f"
sm4 = SmUtil.sm4(HexUtil.decodeHex(sm4KeyHex))
println(sm4.decryptStr(data))

println("done!")
```