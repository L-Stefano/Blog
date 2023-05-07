layui.use(function () {
    var flow = layui.flow;
    // 流加载实例
    flow.load({
        elem: '#demo', // 流加载容器
        end: '没有更多了',
        done: function (page, next) { // 执行下一页的回调
            // 模拟数据插入
            setTimeout(function () {
                var lis = [];
                for (var i = 0; i < 4; i++) {
                    lis.push('<li>' + ((page - 1) * 8 + i + 1) + '</li>')
                }

                // 执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                // pages 为 Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                next(lis.join(''), page < 10); // 此处假设总页数为 10
            }, 520);
        }
    });

});