<template>
  <div id="container">
    <!--顶部导航条 -->
    <div class="am-container header">
      <ul class="message-l">
        <div class="topMessage">
          <div v-if="isLogin" class="menu-hd">
            {{ username }},欢迎您！
          </div>
          <div v-else class="menu-hd">
            <a href="#" target="_top" class="h">亲，请登录</a>
            <a href="#" target="_top">免费注册</a>
          </div>
        </div>
      </ul>
      <ul class="message-r">
        <div class="topMessage home">
          <div class="menu-hd"><a href="index.html" target="_top" class="h">商城首页</a></div>
        </div>
        <div class="topMessage my-shangcheng">
          <div class="menu-hd MyShangcheng">
            <a href="user-center.html" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a>
          </div>
        </div>
        <div class="topMessage mini-cart">
          <div class="menu-hd">
            <a id="mc-menu-hd" href="shopcart.html" target="_top">
              <i class="am-icon-shopping-cart  am-icon-fw"></i>
              <span>购物车</span>
              <strong id="J_MiniCartNum" class="h"></strong>
            </a>
          </div>
        </div>
        <div class="topMessage favorite">
          <div class="menu-hd">
            <!--<a href="#" target="_top"><i class="am-icon-heart am-icon-fw"></i><span>收藏夹</span></a>-->
          </div>
        </div>
      </ul>
    </div>
    <!--悬浮搜索框-->
    <div class="nav white">
      <div class="logo"><img src="static/images/logo.png"/></div>
      <div class="logoBig">
        <li><img src="static/images/logo.png"/></li>
      </div>
      <div class="search-bar pr">
        <a name="index_none_header_sysc" href="#"></a>
        <form>
          <input id="searchInput" name="index_none_header_sysc" type="text" placeholder="搜索" autocomplete="off">
          <input id="ai-topsearch" class="submit am-btn" value="搜索" index="1" type="submit">
        </form>
      </div>
    </div>
    <div class="clear"></div>

    <b class="line"></b>

    <div class="listMain">

      <!--分类-->
      <div class="nav-table">
        <div class="long-title"><span class="all-goods">全部分类</span></div>
        <div class="nav-cont">
          <ul>
            <li class="index"><a href="#">首页</a></li>
            <li class="qc"><a href="#">闪购</a></li>
            <li class="qc"><a href="#">限时抢</a></li>
            <li class="qc"><a href="#">团购</a></li>
            <li class="qc last"><a href="#">大包装</a></li>
          </ul>
          <div class="nav-extra">
            <i class="am-icon-user-secret am-icon-md nav-user"></i><b></b>我的福利
            <i class="am-icon-angle-right" style="padding-left: 10px;"></i>
          </div>
        </div>
      </div>
      <ol class="am-breadcrumb am-breadcrumb-slash">
        <li><a href="#">首页</a></li>
        <li><a href="#">分类</a></li>
        <li class="am-active">内容</li>
      </ol>
      <div class="scoll">
        <section class="slider">
          <div class="flexslider">
            <ul class="slides">
              <li>
                <img src="/src/assets/images/01.jpg" title="pic"/>
              </li>
              <li>
                <img src="/src/assets/images/02.jpg"/>
              </li>
              <li>
                <img src="/src/assets/images/03.jpg"/>
              </li>
            </ul>
          </div>
        </section>
      </div>

      <!--放大镜-->
      <div class="item-inform">
        <div class="clearfixLeft" id="clearcontent">

          <div class="box">
            <template v-if="productImgs.length>0">
              <div class="tb-booth tb-pic tb-s310">
                <a href="/src/assets/images/01.jpg">
                  <img :src="'static/pimgs/'+productImgs[0].url" alt="细节展示放大镜特效"
                       :rel="'static/pimgs/'+productImgs[0].url" class="jqzoom"/>
                </a>
              </div>
            </template>
            <ul class="tb-thumb" id="thumblist">
              <template v-for="(img,index) in productImgs">
                <li :class="{'tb-selected':index===0}">
                  <div class="tb-pic tb-s40">
                    <a href="#"><img :src="'static/pimgs/'+img.url" :mid="'static/pimgs/'+img.url"
                                     :big="'static/pimgs/'+img.url"></a>
                  </div>
                </li>
              </template>
            </ul>
          </div>

          <div class="clear"></div>
        </div>

        <div class="clearfixRight">

          <!--规格属性-->
          <!--名称-->
          <div class="tb-detail-hd">
            <h1>{{ product.productName }}</h1>
          </div>

          <div class="tb-detail-list">
            <!--价格-->
            <div class="tb-detail-price">
              <li class="price iteminfo_price">
                <dt>促销价</dt>
                <dd><em>¥</em><b class="sys_item_price">{{ productSkus[currentSkuIndex].sellPrice }}</b></dd>
              </li>
              <li class="price iteminfo_mktprice">
                <dt>原价</dt>
                <dd><em>¥</em><b class="sys_item_mktprice">{{ productSkus[currentSkuIndex].originalPrice }}</b></dd>
              </li>
              <div class="clear"></div>
            </div>

            <!--地址-->
            <dl class="iteminfo_parameter freight">
              <dt>配送至</dt>
              <div class="iteminfo_freprice">
                <div class="am-form-content address">
                  <select data-am-selected>
                    <option value="a">浙江省</option>
                    <option value="b">湖北省</option>
                  </select>
                  <select data-am-selected>
                    <option value="a">温州市</option>
                    <option value="b">武汉市</option>
                  </select>
                  <select data-am-selected>
                    <option value="a">瑞安区</option>
                    <option value="b">洪山区</option>
                  </select>
                </div>
                <div class="pay-logis">
                  快递<b class="sys_item_freprice">10</b>元
                </div>
              </div>
            </dl>
            <div class="clear"></div>

            <!--销量-->
            <ul class="tm-ind-panel">
              <!--<li class="tm-ind-item tm-ind-sellCount canClick">
                <div class="tm-indcon"><span class="tm-label">月销量</span><span class="tm-count">1015</span></div>
              </li>-->
              <li class="tm-ind-item tm-ind-sumCount canClick">
                <div class="tm-indcon"><span class="tm-label">累计销量</span><span
                    class="tm-count">{{ prodcts.soldNum }}</span></div>
              </li>
              <li class="tm-ind-item tm-ind-reviewCount canClick tm-line3">
                <div class="tm-indcon"><span class="tm-label">累计评价</span><span class="tm-count">640</span></div>
              </li>
            </ul>
            <div class="clear"></div>

            <!--各种规格-->
            <div class="iteminfo_parameter sys_item_specpara">
              <dt class="theme-login">
                <div class="cart-title">可选规格<span class="am-icon-angle-right"></span></div>
              </dt>
              <dd>
                <!--操作页面-->

                <div class="theme-popover-mask"></div>

                <div class="theme-popover">
                  <div class="theme-span"></div>
                  <div class="theme-poptit">
                    <a href="javascript:;" title="关闭" class="close">×</a>
                  </div>
                  <div class="theme-popbod dform">
                    <form class="theme-signin" name="loginform" action="" method="post">

                      <div class="theme-signin-left">
                        <!--显示商品套餐名选项（当套餐数量>1时显示）-->
                        <div v-if="productSkus.length > 1" class="theme-options">
                          <div class="cart-title">套餐</div>
                          <ul>
                            <template v-for="sku,index in productSkus">
                              <!--如果当前套餐是正在显示的套餐，则添加选中效果-->
                              <li v-if="index==currentSkuIndex" class="sku-line selected" :data-index="index"
                                  @click="changeSku">{{ sku.skuName }}<i></i></li>
                              <!--否则不添加选中效果-->
                              <li v-else class="sku-line" :data-index="index" @click="changeSku">
                                {{ sku.skuName }}<i></i></li>
                            </template>
                          </ul>
                        </div>

                        <!--显示当前套餐下的属性  skuProps  对象/Map-->
                        <div id="props">
                          <div v-for="(value,key) in skuProps" class="theme-options">
                            <div class="cart-title">{{ key }}</div>
                            <ul>
                              <template v-for="v,index in value">
                                <li :class="{'sku-line':true,'selected':index===0}" :name="key" @click="changeProp"
                                    :data-key="key" :data-v="v">{{ v }}
                                </li>
                              </template>
                            </ul>
                          </div>
                        </div>
                        <!--
                        <div id="props">
                          <div v-for="(value,key) in skuProps" class="theme-options" >
                            <div class="cart-title">口味</div>
                            <ul>
                              <template v-for="v,index in value">
                                <li name="口味" data-key="口味" data-v="原味">原味</li>
                                <li name="口味" data-key="口味" data-v="烧烤">烧烤</li>
                                <li name="口味" data-key="口味" data-v="孜然">孜然</li>
                              </template>
                            </ul>
                          </div>
                          <div v-for="(value,key) in skuProps" class="theme-options" >
                            <div class="cart-title">包装</div>
                            <ul>
                              <template v-for="v,index in value">
                                <li  name="包装" data-key="包装" data-v="新包装">新包装</li>
                                <li  name="包装" data-key="包装" data-v="老包装">老包装</li>
                              </template>
                            </ul>
                          </div>
                        </div>


                        -->


                        <div class="theme-options">
                          <div class="cart-title number">数量</div>
                        </div>
                      </div>
                    </form>
                  </div>
                </div>
              </dd>
              <dd>
                <input id="min" class="am-btn am-btn-default" type="button" value="-" @click="changeNum(-1)"/>
                <input id="text_box" type="text" v-model="num" style="width:30px;"/>
                <input id="add" class="am-btn am-btn-default" type="button" value="+" @click="changeNum(1)"/>
                <span id="stock1" class="tb-hidden">库存<span
                    class="stock">{{ productSkus[currentSkuIndex].stock }}</span>件</span>
              </dd>
            </div>
          </div>

          <div class="clear"></div>

          <div class="btn-op">
            <div class="btn am-btn am-btn-warning">确认</div>
            <div class="btn close am-btn am-btn-warning">取消</div>
          </div>
        </div>
        <div class="theme-signin-right">
          <div class="img-info">
            <img src="static/images/songzi.jpg"/>
          </div>
          <div class="text-info">
            <span class="J_Price price-now">¥39.00</span>
            <span id="Stock" class="tb-hidden">库存<span class="stock">1000</span>件</span>
          </div>
        </div>

      </div>
    </div>
    <div class="clear"></div>

    <!--活动	-->
    <div class="shopPromotion gold">
      <div class="hot">
        <dt class="tb-metatit">折扣</dt>
        <div class="gold-list">
          <p>当前套餐享{{ productSkus[currentSkuIndex].discounts * 10 }}折优惠
            <!--<span>点击领券<i class="am-icon-sort-down"></i></span>--></p>
        </div>
      </div>
      <div class="clear"></div>
      <div class="coupon">
        <dt class="tb-metatit">优惠券</dt>
        <div class="gold-list">
          <ul>
            <li>125减5</li>
            <li>198减10</li>
            <li>298减20</li>
          </ul>
        </div>
      </div>
    </div>
  </div>


  <div class="pay">
    <div class="pay-opt">
      <a href="home.html"><span class="am-icon-home am-icon-fw">首页</span></a>
      <a><span class="am-icon-heart am-icon-fw">收藏</span></a>

    </div>
    <li>
      <div class="clearfix tb-btn tb-btn-buy theme-login">
        <a id="LikBuy" title="点此按钮到下一步确认购买信息" href="#">立即购买</a>
      </div>
    </li>
    <li>
      <div class="clearfix tb-btn tb-btn-basket theme-login">
        <a id="LikBasket" title="加入购物车" @click="addShopCart"><i></i>加入购物车</a>
      </div>
    </li>
  </div>
  <div class="clear"></div>


  <!--优惠套装-->
  <!--<div class="match">
    <div class="match-title">优惠套装</div>
    <div class="match-comment">
      <ul class="like_list">
        <li>
          <div class="s_picBox">
            <a class="s_pic" href="#"><img src="static/images/cp.jpg"></a>
          </div> <a class="txt" target="_blank" href="#">萨拉米 1+1小鸡腿</a>
          <div class="info-box"> <span class="info-box-price">¥ 29.90</span> <span class="info-original-price">￥ 199.00</span> </div>
        </li>
        <li class="plus_icon"><i>+</i></li>
        <li>
          <div class="s_picBox">
            <a class="s_pic" href="#"><img src="static/images/cp2.jpg"></a>
          </div> <a class="txt" target="_blank" href="#">ZEK 原味海苔</a>
          <div class="info-box"> <span class="info-box-price">¥ 8.90</span> <span class="info-original-price">￥ 299.00</span> </div>
        </li>
        <li class="plus_icon"><i>=</i></li>
        <li class="total_price">
          <p class="combo_price"><span class="c-title">套餐价:</span><span>￥35.00</span> </p>
          <p class="save_all">共省:<span>￥463.00</span></p> <a href="#" class="buy_now">立即购买</a> </li>
        <li class="plus_icon"><i class="am-icon-angle-right"></i></li>
      </ul>
    </div>
  </div>-->
  <div class="clear"></div>


  <!-- introduce-->
  <div class="introduce">
    <div class="browse">
      <div class="mc">
        <ul>
          <div class="mt">
            <h2>看了又看</h2>
          </div>

          <li class="first">
            <div class="p-img">
              <a href="#"> <img class="" src="static/images/browse1.jpg"> </a>
            </div>
            <div class="p-name"><a href="#">
              【三只松鼠_开口松子】零食坚果特产炒货东北红松子原味
            </a>
            </div>
            <div class="p-price"><strong>￥35.90</strong></div>
          </li>
          <li>
            <div class="p-img">
              <a href="#"> <img class="" src="static/images/browse1.jpg"> </a>
            </div>
            <div class="p-name"><a href="#">
              【三只松鼠_开口松子】零食坚果特产炒货东北红松子原味
            </a>
            </div>
            <div class="p-price"><strong>￥35.90</strong></div>
          </li>
          <li>
            <div class="p-img">
              <a href="#"> <img class="" src="static/images/browse1.jpg"> </a>
            </div>
            <div class="p-name"><a href="#">
              【三只松鼠_开口松子】零食坚果特产炒货东北红松子原味
            </a>
            </div>
            <div class="p-price"><strong>￥35.90</strong></div>
          </li>
          <li>
            <div class="p-img">
              <a href="#"> <img class="" src="static/images/browse1.jpg"> </a>
            </div>
            <div class="p-name"><a href="#">
              【三只松鼠_开口松子】零食坚果特产炒货东北红松子原味
            </a>
            </div>
            <div class="p-price"><strong>￥35.90</strong></div>
          </li>
          <li>
            <div class="p-img">
              <a href="#"> <img class="" src="static/images/browse1.jpg"> </a>
            </div>
            <div class="p-name"><a href="#">
              【三只松鼠_开口松子218g】零食坚果特产炒货东北红松子原味
            </a>
            </div>
            <div class="p-price"><strong>￥35.90</strong></div>
          </li>

        </ul>
      </div>
    </div>
    <div class="introduceMain">
      <div class="am-tabs" data-am-tabs>
        <ul class="am-avg-sm-3 am-tabs-nav am-nav am-nav-tabs">
          <li class="am-active">
            <a href="#"><span class="index-needs-dt-txt">宝贝详情</span></a>
          </li>
          <li>
            <a href="#"><span class="index-needs-dt-txt">全部评价</span></a>
          </li>
          <li>
            <a href="#"><span class="index-needs-dt-txt">猜你喜欢</span></a>
          </li>
        </ul>

        <div class="am-tabs-bd">
          <!--商品参数和商品细节-->
          <div class="am-tab-panel am-fade am-in am-active">
            <div class="J_Brand">

              <div class="attr-list-hd tm-clear">
                <h4>产品参数：</h4></div>
              <div class="clear"></div>
              <ul id="J_AttrUL">
                <li title="">产地:&nbsp;{{ productParams.productPlace }}</li>
                <li title="">保质期:&nbsp;{{ productParams.footPeriod }}</li>
                <li title="">品牌:&nbsp;{{ productParams.brand }}</li>
                <li title="">生产厂商:&nbsp;{{ productParams.factoryName }}</li>
                <li title="">生产地址:&nbsp;{{ productParams.factoryAddress }}</li>
                <li title="">包装方式:&nbsp;{{ productParams.packagingMethod }}</li>
                <li title="">规格重量:&nbsp;{{ productParams.weight }}</li>
                <li title="">储存方式：&nbsp;{{ productParams.storageMethod }}</li>
                <li title="">食用方式：&nbsp;{{ productParams.eatMethod }}</li>
              </ul>
              <div class="clear"></div>
            </div>

            <div class="details">
              <div class="attr-list-hd after-market-hd">
                <h4>商品细节</h4>
              </div>
              <div class="twlistNews">
                <span v-html="product.content"></span>
              </div>
            </div>
            <div class="clear"></div>

          </div>

          <!--商品评价-->
          <div class="am-tab-panel am-fade">

            <div class="actor-new">
              <div class="rate">
                <strong>{{ commontsCountMap.percent }}<span>%</span></strong><br> <span>好评度</span>
              </div>
              <dl>
                <dt>买家印象</dt>
                <dd class="p-bfc">
                  <q class="comm-tags"><span>味道不错</span><em>(2177)</em></q>
                  <q class="comm-tags"><span>颗粒饱满</span><em>(1860)</em></q>
                  <q class="comm-tags"><span>口感好</span><em>(1823)</em></q>
                  <q class="comm-tags"><span>商品不错</span><em>(1689)</em></q>
                  <q class="comm-tags"><span>香脆可口</span><em>(1488)</em></q>
                  <q class="comm-tags"><span>个个开口</span><em>(1392)</em></q>
                  <q class="comm-tags"><span>价格便宜</span><em>(1119)</em></q>
                  <q class="comm-tags"><span>特价买的</span><em>(865)</em></q>
                  <q class="comm-tags"><span>皮很薄</span><em>(831)</em></q>
                </dd>
              </dl>
            </div>
            <div class="clear"></div>
            <div class="tb-r-filter-bar">
              <ul class=" tb-taglist am-avg-sm-4">
                <li class="tb-taglist-li tb-taglist-li-current">
                  <div class="comment-info">
                    <span>全部评价</span><span class="tb-tbcr-num">({{ commontsCountMap.total }})</span>
                  </div>
                </li>

                <li class="tb-taglist-li tb-taglist-li-1">
                  <div class="comment-info">
                    <span>好评</span><span class="tb-tbcr-num">({{ commontsCountMap.goodTotal }})</span>
                  </div>
                </li>

                <li class="tb-taglist-li tb-taglist-li-0">
                  <div class="comment-info">
                    <span>中评</span><span class="tb-tbcr-num">({{ commontsCountMap.midTotal }})</span>
                  </div>
                </li>

                <li class="tb-taglist-li tb-taglist-li--1">
                  <div class="comment-info">
                    <span>差评</span><span class="tb-tbcr-num">({{ commontsCountMap.badTotal }})</span>
                  </div>
                </li>
              </ul>
            </div>
            <div class="clear"></div>

            <ul class="am-comments-list am-comments-list-flip">
              <template v-for="commont in productCommonts">
                <li class="am-comment">
                  <!-- 评论容器 -->
                  <a href="">
                    <!-- 评论者头像 -->
                    <img class="am-comment-avatar" :src="'static/'+commont.userImg"/>
                  </a>

                  <div class="am-comment-main">
                    <!-- 评论内容容器 -->
                    <header class="am-comment-hd">
                      <!--<h3 class="am-comment-title">评论标题</h3>-->
                      <div class="am-comment-meta">
                        <!-- 评论元数据 -->
                        <a href="#link-to-user" class="am-comment-author">
                          {{
                            commont.username.substring(0, 2) + '*****' + commont.username.substring(commont.username.length - 1)
                          }}
                          <span v-if="commont.isAnonymous == 1">(匿名)</span>
                          <span v-else>({{ commont.nickname }})</span>
                        </a>
                        <!-- 评论者 -->
                        评论于
                        <time datetime="">{{ commont.sepcName.substring(0, 10) }}</time>
                      </div>
                    </header>

                    <div class="am-comment-bd">
                      <div class="tb-rev-item " data-id="255776406962">
                        <div class="J_TbcRate_ReviewContent tb-tbcr-content ">
                          {{ commont.commContent }}
                        </div>
                        <!--<div class="tb-r-act-bar">
                          颜色分类：柠檬黄&nbsp;&nbsp;尺码：S
                        </div>-->
                        <div v-if="commont.replyStatus==1" class="tb-r-act-bar">
                          <hr>
                          <label style="color: gray; font-weight: bold;">商家回复：</label>
                          <time datetime="">回复于{{ commont.replyTime.substring(0, 10) }}</time>
                          <br/>
                          {{ commont.replyContent }}
                        </div>
                      </div>

                    </div>
                    <!-- 评论内容 -->
                  </div>
                </li>
              </template>

            </ul>

            <div class="clear"></div>

            <!--分页 -->
            <el-pagination background layout="prev, pager, next" :current-page="pageNum"
                           :page-size="limit" :total="count"
                           @current-change="pager"></el-pagination>
            <!--<ul class="am-pagination am-pagination-right">
              <li class="am-disabled"><a href="#">&laquo;</a></li>
              <li class="am-active"><a href="#">1</a></li>
              <li><a href="#">2</a></li>
              <li><a href="#">3</a></li>
              <li><a href="#">4</a></li>
              <li><a href="#">5</a></li>
              <li><a href="#">&raquo;</a></li>
            </ul>-->
            <div class="clear"></div>

            <div class="tb-reviewsft">
              <div class="tb-rate-alert type-attention">购买前请查看该商品的 <a href="#" target="_blank">购物保障</a>，明确您的售后保障权益。
              </div>
            </div>

          </div>

          <!--猜你喜欢-->
          <div class="am-tab-panel am-fade">
            <div class="like">
              <ul class="am-avg-sm-2 am-avg-md-3 am-avg-lg-4 boxes">
                <li>
                  <div class="i-pic limit">
                    <img src="static/images/imgsearch1.jpg"/>
                    <p>【良品铺子_开口松子】零食坚果特产炒货
                      <span>东北红松子奶油味</span></p>
                    <p class="price fl">
                      <b>¥</b>
                      <strong>298.00</strong>
                    </p>
                  </div>
                </li>
                <li>
                  <div class="i-pic limit">
                    <img src="static/images/imgsearch1.jpg"/>
                    <p>【良品铺子_开口松子】零食坚果特产炒货
                      <span>东北红松子奶油味</span></p>
                    <p class="price fl">
                      <b>¥</b>
                      <strong>298.00</strong>
                    </p>
                  </div>
                </li>
                <li>
                  <div class="i-pic limit">
                    <img src="static/images/imgsearch1.jpg"/>
                    <p>【良品铺子_开口松子】零食坚果特产炒货
                      <span>东北红松子奶油味</span></p>
                    <p class="price fl">
                      <b>¥</b>
                      <strong>298.00</strong>
                    </p>
                  </div>
                </li>
                <li>
                  <div class="i-pic limit">
                    <img src="static/images/imgsearch1.jpg"/>
                    <p>【良品铺子_开口松子】零食坚果特产炒货
                      <span>东北红松子奶油味</span></p>
                    <p class="price fl">
                      <b>¥</b>
                      <strong>298.00</strong>
                    </p>
                  </div>
                </li>
                <li>
                  <div class="i-pic limit">
                    <img src="static/images/imgsearch1.jpg"/>
                    <p>【良品铺子_开口松子】零食坚果特产炒货
                      <span>东北红松子奶油味</span></p>
                    <p class="price fl">
                      <b>¥</b>
                      <strong>298.00</strong>
                    </p>
                  </div>
                </li>
                <li>
                  <div class="i-pic limit">
                    <img src="static/images/imgsearch1.jpg"/>
                    <p>【良品铺子_开口松子】零食坚果特产炒货
                      <span>东北红松子奶油味</span></p>
                    <p class="price fl">
                      <b>¥</b>
                      <strong>298.00</strong>
                    </p>
                  </div>
                </li>
                <li>
                  <div class="i-pic limit">
                    <img src="static/images/imgsearch1.jpg"/>
                    <p>【良品铺子_开口松子】零食坚果特产炒货
                      <span>东北红松子奶油味</span></p>
                    <p class="price fl">
                      <b>¥</b>
                      <strong>298.00</strong>
                    </p>
                  </div>
                </li>
                <li>
                  <div class="i-pic limit">
                    <img src="static/images/imgsearch1.jpg"/>
                    <p>【良品铺子_开口松子】零食坚果特产炒货
                      <span>东北红松子奶油味</span></p>
                    <p class="price fl">
                      <b>¥</b>
                      <strong>298.00</strong>
                    </p>
                  </div>
                </li>
                <li>
                  <div class="i-pic limit">
                    <img src="static/images/imgsearch1.jpg"/>
                    <p>【良品铺子_开口松子】零食坚果特产炒货
                      <span>东北红松子奶油味</span></p>
                    <p class="price fl">
                      <b>¥</b>
                      <strong>298.00</strong>
                    </p>
                  </div>
                </li>
                <li>
                  <div class="i-pic limit">
                    <img src="static/images/imgsearch1.jpg"/>
                    <p>【良品铺子_开口松子】零食坚果特产炒货
                      <span>东北红松子奶油味</span></p>
                    <p class="price fl">
                      <b>¥</b>
                      <strong>298.00</strong>
                    </p>
                  </div>
                </li>
                <li>
                  <div class="i-pic limit">
                    <img src="static/images/imgsearch1.jpg"/>
                    <p>【良品铺子_开口松子】零食坚果特产炒货
                      <span>东北红松子奶油味</span></p>
                    <p class="price fl">
                      <b>¥</b>
                      <strong>298.00</strong>
                    </p>
                  </div>
                </li>
                <li>
                  <div class="i-pic limit">
                    <img src="static/images/imgsearch1.jpg"/>
                    <p>【良品铺子_开口松子】零食坚果特产炒货
                      <span>东北红松子奶油味</span></p>
                    <p class="price fl">
                      <b>¥</b>
                      <strong>298.00</strong>
                    </p>
                  </div>
                </li>
              </ul>
            </div>
            <div class="clear"></div>

            <!--分页 -->
            <ul class="am-pagination am-pagination-right">
              <li class="am-disabled"><a href="#">&laquo;</a></li>
              <li class="am-active"><a href="#">1</a></li>
              <li><a href="#">2</a></li>
              <li><a href="#">3</a></li>
              <li><a href="#">4</a></li>
              <li><a href="#">6</a></li>
              <li><a href="#">&raquo;</a></li>
            </ul>
            <div class="clear"></div>

          </div>

        </div>

      </div>

      <div class="clear"></div>

      <div class="footer">
        <div class="footer-hd">
          <p>
            <a href="#">商城</a>
            <b>|</b>
            <a href="#">商城首页</a>
            <b>|</b>
            <a href="#">支付宝</a>
            <b>|</b>
            <a href="#">物流</a>
          </p>
        </div>
        <div class="footer-bd">
          <p>
            <a href="#">关于</a>
            <a href="#">合作伙伴</a>
            <a href="#">联系我们</a>
            <a href="#">网站地图</a>
            <em>版权所有</em>
          </p>
        </div>
      </div>
    </div>
  </div>
  <!--菜单 -->
  <div class=tip>
    <div id="sidebar">
      <div id="wrap">
        <div id="prof" class="item">
          <a href="#">
            <span class="setting"></span>
          </a>
          <div class="ibar_login_box status_login">
            <div class="avatar_box">
              <p class="avatar_imgbox"><img src="/src/assets/images/no-img_mid_.jpg"/></p>
              <ul class="user_info">
                <li>用户名：sl1903</li>
                <li>级&nbsp;别：普通会员</li>
              </ul>
            </div>
            <div class="login_btnbox">
              <a href="#" class="login_order">我的订单</a>
              <a href="#" class="login_favorite">我的收藏</a>
            </div>
            <i class="icon_arrow_white"></i>
          </div>

        </div>
        <div id="shopCart" class="item">
          <a href="shopcart.html">
            <span class="message"><p>购物车</p></span>
          </a>
          <p class="cart_num">0</p>
        </div>
        <div id="asset" class="item">
          <a href="#">
            <span class="view"></span>
          </a>
          <div class="mp_tooltip">
            我的资产
            <i class="icon_arrow_right_black"></i>
          </div>
        </div>

        <div id="foot" class="item">
          <a href="#">
            <span class="zuji"></span>
          </a>
          <div class="mp_tooltip">
            我的足迹
            <i class="icon_arrow_right_black"></i>
          </div>
        </div>

        <div id="brand" class="item">
          <a href="#">
            <span class="wdsc"><img src="/src/assets/images/wdsc.png"/></span>
          </a>
          <div class="mp_tooltip">
            我的收藏
            <i class="icon_arrow_right_black"></i>
          </div>
        </div>

        <div id="broadcast" class="item">
          <a href="#">
            <span class="chongzhi"><img src="/src/assets/images/chongzhi.png"/></span>
          </a>
          <div class="mp_tooltip">
            我要充值
            <i class="icon_arrow_right_black"></i>
          </div>
        </div>

        <div class="quick_toggle">
          <li class="qtitem">
            <a href="#"><span class="kfzx"></span></a>
            <div class="mp_tooltip">客服中心<i class="icon_arrow_right_black"></i></div>
          </li>
          <!--二维码 -->
          <li class="qtitem">
            <a href="#none"><span class="mpbtn_qrcode"></span></a>
            <div class="mp_qrcode" style="display:none;"><img src="/src/assets/images/weixin_code_145.png"/><i
                class="icon_arrow_white"></i></div>
          </li>
          <li class="qtitem">
            <a href="#top" class="return_top"><span class="top"></span></a>
          </li>
        </div>

        <!--回到顶部 -->
        <div id="quick_links_pop" class="quick_links_pop hide"></div>

      </div>

    </div>
    <div id="prof-content" class="nav-content">
      <div class="nav-con-close">
        <i class="am-icon-angle-right am-icon-fw"></i>
      </div>
      <div>
        我
      </div>
    </div>
    <div id="shopCart-content" class="nav-content">
      <div class="nav-con-close">
        <i class="am-icon-angle-right am-icon-fw"></i>
      </div>
      <div>
        购物车
      </div>
    </div>
    <div id="asset-content" class="nav-content">
      <div class="nav-con-close">
        <i class="am-icon-angle-right am-icon-fw"></i>
      </div>
      <div>
        资产
      </div>

      <div class="ia-head-list">
        <a href="#" target="_blank" class="pl">
          <div class="num">0</div>
          <div class="text">优惠券</div>
        </a>
        <a href="#" target="_blank" class="pl">
          <div class="num">0</div>
          <div class="text">红包</div>
        </a>
        <a href="#" target="_blank" class="pl money">
          <div class="num">￥0</div>
          <div class="text">余额</div>
        </a>
      </div>

    </div>
    <div id="foot-content" class="nav-content">
      <div class="nav-con-close">
        <i class="am-icon-angle-right am-icon-fw"></i>
      </div>
      <div>
        足迹
      </div>
    </div>
    <div id="brand-content" class="nav-content">
      <div class="nav-con-close">
        <i class="am-icon-angle-right am-icon-fw"></i>
      </div>
      <div>
        收藏
      </div>
    </div>
    <div id="broadcast-content" class="nav-content">
      <div class="nav-con-close">
        <i class="am-icon-angle-right am-icon-fw"></i>
      </div>
      <div>
        充值
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: "temp"
}
</script>

<style scoped>

</style>