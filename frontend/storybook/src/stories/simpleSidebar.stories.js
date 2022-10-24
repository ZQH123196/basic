import SimpleSidebar from "../components/simpleSidebar/index.vue"

export default {
	title: "SimpleSidebar",
	component: SimpleSidebar
}


const Template = (args) => ({
  // Components used in your story `template` are defined in the `components` object
  components: { SimpleSidebar },
  // The story's `args` need to be mapped into the template through the `setup()` method
  setup() {
    // Story args can be mapped to keys in the returned object
    return {  };
  },
  // Then, those values can be accessed directly in the template
  template: '<SimpleSidebar />',
})


export const siderbarBase = Template.bind({})
siderbarBase.args = {

}


export const siderbarCollapse = Template.bind({})
siderbarCollapse.args = {

}

