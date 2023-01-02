export interface TreeNode4Front {
    label: string;
    children?: TreeNode4Front[] | [];
}

export interface TreeNode4Map {
    [propname:string]: {
        nodeName: string,
        nodePath: string,
        children?: TreeNode4Map
    }
}

export interface TreeNode4Array {
    nodeName: string,
    nodePath: string,
    children?: TreeNode4Array[]
}

export function walkTree4Map(node: TreeNode4Map, tree: TreeNode4Front[]) {
    const keys = Object.keys(node)
    keys.forEach(k => {
        let item = node[k]
        let newValue: TreeNode4Front = {
            label: item.nodePath            
        }

        tree.push(newValue)
        if (item.children) {
            newValue.children = []
            walkTree4Map(item.children, newValue.children)
        }
    })
};


export function walkTree4Array(node: TreeNode4Array[], tree: TreeNode4Front[]) {
    node.forEach(item => {
        let newValue: TreeNode4Front = {
            label: item.nodePath            
        }
        tree.push(newValue)
        if (item.children) {
            newValue.children = []
            walkTree4Array(item.children, newValue.children)
        }
    })
};

