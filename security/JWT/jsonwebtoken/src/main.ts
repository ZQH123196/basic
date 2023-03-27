import * as counter from './counter'
import jwt from "jsonwebtoken"






const dynamicModule = await import("./counter")
console.log(dynamicModule);
console.log(dynamicModule.a);

import("./counter").then((module => {
  console.log(module);
}))
