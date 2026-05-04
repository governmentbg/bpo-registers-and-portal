import { isInternalLink, ProcessEnvironments } from "@duosoftbg/bpo-components";
import { Link } from "react-router-dom";
import React from "react";

const InternalNavigation = ({ action, actionTarget, children }) => {
  const publicUrl = ProcessEnvironments.PublicUrl;
  const internalUrl = action.substring(action.indexOf(publicUrl) + publicUrl.length);

  return (
    <Link style={{ textDecoration: "none" }} to={internalUrl} target={actionTarget}>
      {children}
    </Link>
  );
};

const ExternalNavigation = ({ action, actionTarget, children }) => {
  return (
    <a style={{ textDecoration: "none" }} href={action} target={actionTarget}>
      {children}
    </a>
  );
};

const CardNavigation = ({ action, actionTarget, children }) => {
  if (isInternalLink(action)) {
    return <InternalNavigation action={action} actionTarget={actionTarget} children={children} />;
  }
  return <ExternalNavigation action={action} actionTarget={actionTarget} children={children} />;
};

export default CardNavigation;
