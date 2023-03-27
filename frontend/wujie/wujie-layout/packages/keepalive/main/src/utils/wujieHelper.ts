import { defineComponent, h, render } from "vue";
import { Router, RouteRecordRaw } from "vue-router";
import { bus, setupApp, preloadApp, startApp as wujieStartApp, destroyApp } from "wujie";

/**
 * 参考 wujie vue3 实现
 * 根据 routeMap 生成 wujievue 实例并挂入 vueRouter
 * 一个路由生成一个新的 wujie vue 实例
 * 
 */
function generateWujieComponent(route: RouteRecordRaw) {
    const name = route.name.toString()
    const url = route.path.toString()
    let wujieVueOptions = {
        name: name,
        props: {
            width: {
                type: String,
                default: ""
            },
            height: {
                type: String,
                default: ""
            },
            name: {
                type: String,
                default: name
            },
            loading: {
                type: HTMLElement,
                default: undefined
            },
            url: {
                type: String,
                default: ""
            },
            sync: {
                type: Boolean,
                default: undefined
            },
            prefix: {
                type: Object,
                default: undefined
            },
            alive: {
                type: Boolean,
                default: undefined
            },
            props: {
                type: Object,
                default: undefined
            },
            attrs: {
                type: Object,
                default: undefined
            },
            replace: {
                type: Function,
                default: undefined
            },
            fetch: {
                type: Function,
                default: undefined
            },
            fiber: {
                type: Boolean,
                default: undefined
            },
            degrade: {
                type: Boolean,
                default: undefined
            },
            plugins: {
                type: Array,
                default: null
            },
            beforeLoad: {
                type: Function,
                default: null
            },
            beforeMount: {
                type: Function,
                default: null
            },
            afterMount: {
                type: Function,
                default: null
            },
            beforeUnmount: {
                type: Function,
                default: null
            },
            afterUnmount: {
                type: Function,
                default: null
            },
            activated: {
                type: Function,
                default: null
            },
            deactivated: {
                type: Function,
                default: null
            },
            loadError: {
                type: Function,
                default: null
            }
        },
        data: function data() {
            return {
                startAppQueue: Promise.resolve()
            };
        },
        mounted: function mounted() {
            var _this = this;

            bus.$onAll(this.handleEmit);
            this.execStartApp();
            this.$watch(function () {
                return _this.name + _this.url;
            }, function () {
                return _this.execStartApp();
            });
        },
        methods: {
            handleEmit: function handleEmit(event) {
                for (var _len = arguments.length, args = new Array(_len > 1 ? _len - 1 : 0), _key = 1; _key < _len; _key++) {
                    args[_key - 1] = arguments[_key];
                }

                this.$emit.apply(this, [event].concat(args));
            },
            startApp: function startApp() {
                var _this2 = this;
                return wujieStartApp({
                    name: _this2.name,
                    url: _this2.url,
                    el: _this2.$refs.wujie,
                    loading: _this2.loading,
                    alive: _this2.alive,
                    fetch: _this2.fetch,
                    props: _this2.props,
                    attrs: _this2.attrs,
                    replace: _this2.replace,
                    sync: _this2.sync,
                    prefix: _this2.prefix,
                    fiber: _this2.fiber,
                    degrade: _this2.degrade,
                    plugins: _this2.plugins,
                    beforeLoad: _this2.beforeLoad,
                    beforeMount: _this2.beforeMount,
                    afterMount: _this2.afterMount,
                    beforeUnmount: _this2.beforeUnmount,
                    afterUnmount: _this2.afterUnmount,
                    activated: _this2.activated,
                    deactivated: _this2.deactivated,
                    loadError: _this2.loadError
                });
            },
            execStartApp: function execStartApp() {
                this.startAppQueue = this.startAppQueue.then(this.startApp);
            },
            destroy: function destroy() {
                destroyApp(this.name);
            }
        },
        beforeDestroy: function beforeDestroy() {
            bus.$offAll(this.handleEmit);
        },
        render: function render() {
            return h("div", {
                style: {
                    width: this.width,
                    height: this.height
                },
                ref: "wujie"
            });
        }
    };
    // 动态创建 vue 组件
    let MyCustomWujieVue = defineComponent(wujieVueOptions);
    h(MyCustomWujieVue)
    // MyCustomWujieVue.bind()
    return MyCustomWujieVue;
}



export {
    generateWujieComponent
}