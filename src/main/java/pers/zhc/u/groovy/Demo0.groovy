package pers.zhc.u.groovy

class Demo0 {
    private def i = 0

    static void main(String[] args) {
        f()
    }

    static def f() {
        def o = new Demo0()
        def t = []
        for (int i = 0; i < 10; i++) {
            t.add(new Thread({
                for (int j = 0; j < 100; j++) {
                    o.func()
                }
            }))
        }
        for (a in t) {
            (a as Thread).start()
        }
        for (a in t) {
            (a as Thread).join()
        }
        println "o.i = $o.i"
        return o.i
    }

    private synchronized func() {
        ++this.i
    }
}