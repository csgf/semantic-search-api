*******************
SEMANTIC SEARCH API
*******************

============
About
============


Programmable use of the CHAIN-REDS Semantic Search Engine is possible thanks to a simple RESTful API. The API allows to get and reuse the millions of open access resources contained  in the `CHAIN-REDS Knowledge Base <https://www.chain-project.eu/knowledge-base>`_ and stored in a `Virtuoso <http://virtuoso.openlinksw.com>`_ RDF-compliant database.

 
============
Usage
============


Get all information about the resources
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- .. line-block:: REQUEST (HTTP method GET) 

     To do a request one must insert into the URL three parameters:

     1.  **keyword**: is the keyword seeked; the keyword can contain any filters for    example (keyword=author:SEEKED_AUTHOR, keyword=subject:SEEKED_SUBJECT, keyword=type:SEEKED_TYPE, keyword=format:SEEKED_FORMAT and keyword=publisher:SEEKED_PUBLISHER)

     2. **limit**: is  the maximum number of resources that has to be retrieved by the query;

     3.  **offset**: is the value in the list of resources to start the retrieval with.

     as shown below:

    http://www.chain-project.eu/virtuoso/api/resources?
     keyword=SEEKED_KEYWORD
    &
     limit=MAX_NUMBER_OF_RESOURCES
    &
     offset=OFFSET
    
    




- .. line-block:: RESPONSE (application/json)

    A collection of resources is represented as a JSON array of objects containing the information about the resources; a single resource is represented as a JSON object. All parameters are Dublin Core Metadata Elements (see http://dublincore.org/documents/dces/ and http://dublincore.org/documents/dcmi-terms/) except  the repository's parameters that include the information regarding the repository that contains the resource.

 If the keyword is not found, the result is an empty object.
  
 *Example:* Search the first 10 resources (offset=0) that contain the keyword    "eye" inside the title.

 http://www.chain-project.eu/virtuoso/api/resources?keyword=eye&limit=10&offset



Get only authors, titles and id of the resources
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- .. line-block:: REQUEST (HTTP method GET) 

     To do a request one must insert into the URL three parameters:

     1.  **keyword**: is the keyword seeked; the keyword can contain any filters for example (keyword=author:SEEKED_AUTHOR, keyword=subject:SEEKED_SUBJECT, keyword=type:SEEKED_TYPE, keyword=format:SEEKED_FORMAT and keyword=publisher:SEEKED_PUBLISHER)

     2. **limit**: is  the maximum number of resources that has to be retrieved by the query;

     3.  **offset**: is the value in the list of resources to start the retrieval with.

     as shown below:

    http://www.chain-project.eu/virtuoso/api/simpleResources?
    keyword=SEEKED_KEYWORD
    &
    limit=MAX_NUMBER_OF_RESOURCES
    &
    offset=OFFSET
    
    




- .. line-block:: RESPONSE (application/json)

    A collection of resources is represented as a JSON array of objects containing   authors, titles and id about the resources.

  

Get all information about a single resource
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^  

- .. line-block:: REQUEST (HTTP method GET) 

     To do a request one must insert into the URL one parameter:

     1. **id**: is the identifier of the resource inside the triple store Virtuoso; it is a uri
   
  as shown below:

  http://www.chain-project.eu/virtuoso/api/singleResource?id=ID_RESOURCE
  


- .. line-block:: RESPONSE (application/json)

    The response is represented as a JSON object containing all information about the single resource.

 
Get information from Google Scholar by a title
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

- .. line-block:: REQUEST (HTTP method GET) 

     To do a request one must insert into the URL one parameter:
    
     1. **title**: is the title by which to get information from Google Scholar
    
     as shown below:

     http://www.chain-project.eu/virtuoso/api/infoGS?title=SOURCE_TITLE
     


- .. line-block:: RESPONSE (application/json)

    The response is represented as a JSON object containing any information from Google Scholar.






=============
Contributors
=============

Checkout detailed instructions `here <https://www.chain-project.eu/semantic-search-api>`_


Please feel free to contact us any time if you have any questions or comments.

.. _INFN: http://www.ct.infn.it/

:Authors:

 `Rita RICCERI <mailto:rita.ricceri@ct.infn.it>`_ - Italian National Institute of Nuclear Physics (INFN_),

 `Giuseppina INSERRA <mailto:giuseppina.inserra@ct.infn.it>`_ - Italian National Institute of Nuclear Physics (INFN_), 

 `Carla CARRUBBA <mailto:carla.carrubba@ct.infn.it>`_ - Italian National Institute of Nuclear Physics (INFN_)
 


