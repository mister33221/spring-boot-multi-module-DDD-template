# DDD template

## 專案結構
```
Root project 'demo'
|-- Project ':core-domain'
|   \-- Project ':core-domain:system'
\-- Project ':shared-kernel'
    |-- Project ':shared-kernel:common'
    \-- Project ':shared-kernel:dddcore'
```

## 設定檔

### [settings.gradle](settings.gradle)
開新模組時，需注意在 settings.gradle 中有無加入新模組，如下：
```groovy
include '{new-module}'
include '{new-module}:{new-project}'
findProject(':{new-module}:{new-project}')?.name = '{new-project}'
```
如果沒有請補上，否則會造成無法編譯的問題 !

### [gradle.properties](gradle.properties)
裡面有設定Nexus的連線資訊，請依照自己的環境修改

### [maven-repo.gradle](maven-repo.gradle)
設定拉取的Maven 依賴:
1. 本地 Maven 庫 `TODO`
2. Nexus Maven 私庫 `TODO`
3. Maven 中央庫 (https://repo1.maven.org/maven2/)

## Build docker image
