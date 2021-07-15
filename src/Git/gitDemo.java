package Git;

/**
 * 日常命令
 *
 *
 * [git clone XXx] //克隆一份代码到本地仓库
 * [git pull] /把远程库的代码更新到工作台
 * [git add] //把本地的修改加到stage中
 * [git commit -m 'comments here' ] /把stage中的修改提交到本地库
 * [git push ] /本地库的修改提交到远程库中
 * [git checkout master/branch ] //切换到某个分支
 * [git merge master] //假设当前在test分支上面， 把 master分支上的修改同步到test分支上
 * [git push -f] //强制提交到远程分支(慎用)
 *
 *
 * 分支切换及版本回退
 * [git reset --hard xxx] //回退到某个版本
 * [git log] //查看历史提交信息
 * [git branch -r/-a] //查看远程分支/全部分支
 * [git branch -d test //删除本地test分支
 * [git checkout -b test] //新建本地est分支
 * [git push origin -delete test] //删除远程分支
 * [git push -u origin test ] //把本地test和远程test关联井推送
 *
 */
public class gitDemo {
    public static void main(String[] args) {
        /**
         * 创建一个本地的git库
         * 在项目文件夹右键 git Bash here   也可以自己随便创一个文件夹
         *
         * 通过命令 git init把这个文件夹变成Git可管理的仓库
         * 这时你会发现TEST里面多了个.git文件夹，
         * 它是Git用来跟踪和管理版本库的。
         * 如果你看不到，是因为它默认是隐藏文件，
         * 那你就需要设置一下让隐藏文件可见。
         *
         * 这时候你就可以把你的项目粘贴到这个本地Git仓库里面
         * （粘贴后你可以通过git status来查看你当前的状态），
         * 然后通过git add把项目添加到仓库（或git add .把该目录下的所有文件添加到仓库，
         * 注意点是用空格隔开的）。
         * 在这个过程中你其实可以一直使用git status来查看你当前的状态。
         * 出现绿色 的new file ***** 就说明好了
         *
         *
         *
         *
         */

        /**
         * 把项目提交到git仓库
         * git commit -m ""
         * -m后面引号里面是本次提交的注释内容，
         * 这个可以不写，但最好写上，不然会报错，详情自行Google。
         * 好了，我们本地Git仓库这边的工作做完了，
         * 下面就到了连接远程仓库（也就是连接Github）
         *
         */

        /**
         * git和Github 的SSH加密
         * 创建SSH KEY。先看一下你C盘用户目录下有没有.ssh目录，
         * 有的话看下里面有没有id_rsa和id_rsa.pub这两个文件，
         * 有就跳到下一步，没有就通过下面命令创建
         * ssh-keygen -t rsa -C "你的邮箱"
         *
         * 然后一路回车。这时你就会在用户下的.ssh目录里
         * 找到id_rsa和id_rsa.pub这两个文件
         *
         *
         * 在上面创建一个SSH KEY
         *
         */

        /**
         * git与SSH KEY关联
         * git remote add origin “网址”
         *
         *
         * 关联好之后我们就可以把本地库的所有内容推送到远程仓库（也就是Github）上了，通过：
         * $ git push -u origin master//origin  是关联名git remote -v//查看关联名字
         * 由于新建的远程仓库是空的，所以要加上-u这个参数，等远程仓库里面有了内容之后，下次再从本地库上传内容的时候只需下面这样就可以了：
         * $ git push origin master
         *
         *
         * 另外，这里有个坑需要注意一下，就是在上面第七步创建远程仓库的时候，
         * 如果你勾选了Initialize this repository with a README
         * （就是创建仓库的时候自动给你创建一个README文件），
         * 那么到了第九步你将本地仓库内容推送到远程仓库的时候就会报一个
         * failed to push some refs to  https://github.com/guyibang/TEST2.git的错。
         *  这是由于你新创建的那个仓库里面的README文件不在本地仓库目录中，这时我们可以通过以下命令先将内容合并以下：
         * git pull --rebase origin master
         *
         *
         * git remote -v        //查看当前关联
         * git remote rm origin //删除origin关联
         *
         * git remote add 自己随便起名字 关联的地址
         *
         */

        /**
         * 总结：其实只需要进行下面几步就能把本地项目上传到Github
         *
         *      1、在本地创建一个版本库（即文件夹），通过git init把它变成Git仓库；
         *
         *      2、把项目复制到这个文件夹里面，再通过git add .把项目添加到仓库；
         *
         *      3、再通过git commit -m "注释内容"把项目提交到仓库；
         *
         *      4、在Github上设置好SSH密钥后，新建一个远程仓库，通过git remote add origin https://github.com/guyibang/TEST2.git将本地仓库和远程仓库进行关联；
         *
         *      5、最后通过git push -u origin master把本地仓库的项目推送到远程仓库（也就是Github）上；（若新建远程仓库的时候自动创建了README文件会报错，解决办法看上面）。
         *
         *
         *
         *
         */


    }
}
